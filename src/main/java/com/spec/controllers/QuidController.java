package com.spec.controllers;

import com.spec.exceptions.InvalidDataException;
import com.spec.model.Product;
import com.spec.model.Quid;
import com.spec.service.ComponentService;
import com.spec.service.ProductService;
import com.spec.service.QuidService;
import com.spec.utils.QuidUtils;
import com.spec.utils.WebappConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.spec.utils.WebappConstants.error;

/**
 * Created by Svetik on 21/12/2017.
 */
@Controller
public class QuidController extends AbstractController {

    private QuidUtils productCompound;

    private QuidUtils currentCompound;

    private String declaration;


    private final ComponentService componentService;

    private final QuidService quidService;

    private final ProductService productService;

    @Autowired
    public QuidController(ComponentService componentService, QuidService quidService, ProductService productService) {
        this.componentService = componentService;
        this.quidService = quidService;
        this.productService = productService;
    }


    @RequestMapping(value = "/parseQuid", method = RequestMethod.POST)
    public
    @ResponseBody
    String quid(@RequestBody QuidUtils quidUtils) throws ParseException, InvalidDataException {

        String productName = quidUtils.getProductName();

//List<Product> list = productService.findByName(productName);
        Product product = productService.findByName(productName).get(0);

        String quidCol = listToString(quidUtils.getQuidColumn());

        String declaredCol = listToString(quidUtils.getDeclaredColumn());

        String allegrenCol = listToString(quidUtils.getAllergenColumn());

        String functionCol = listToString(quidUtils.getFunctionColumn());

        WebappConstants.allFunctions = quidUtils.getFunctionColumn();


        productCompound = null;
        currentCompound = null;
        declaration = "";


        List<String> arrQuidNames = quidUtils.getArrQuidNames();

        List<QuidUtils> quidded;
        List<QuidUtils> declared;


        //Fill up te quid database


        if (quidService.findByProductId(product.getId()).isEmpty()) {


            quidService.save(new com.spec.model.Quid(product.getId(), quidCol, declaredCol,
                    allegrenCol, functionCol));
        } else {

            //update
            Quid quid = quidService.findByProductId(productService.findByName(productName).get(0).getId()).get(0);

            quid.setQuidded(quidCol);
            quid.setDeclared(declaredCol);
            quid.setAllergen(allegrenCol);
            quid.setFunction(functionCol);

            quidService.update(quid);


        }


        List<String> arrDeclaredNames = quidUtils.getArrDeclaredNames();


        declaration = isError(arrQuidNames, arrDeclaredNames);


        if ("".equals(declaration)) {

            quidded = quid(quidUtils.getArrQuidNames(), quidUtils.getArrQuidPerc(), quidUtils.getArrQuidOrder());

            declared = getDeclared(quidUtils.getArrDeclaredNames(), quidUtils.getArrParent(), quidUtils.getArrOrder(), quidUtils.getArrFunctions(), quidUtils.getArrAllergens());

            for (int i = 0; i < declared.size(); i++) {

                if (isQuidded(quidded, declared.get(i).getDeclaredName())) {

                    handleQuiddedAndDeclared(quidded, declared, productName, i);
                } else {

                    handleOnlyDeclared(declared, productName, i);
                }


            }


        }

        Quid quid = quidService.findByProductId(productService.findByName(productName).get(0).getId()).get(0);

        quid.setDeclaration(declaration);
        quid.setDeclarationEditable(declaration);
        quidService.update(quid);


        return declaration;

    }


    @RequestMapping(value = "/saveDeclarationEditable", method = RequestMethod.POST)

    public String saveDeclarationEditable(@RequestParam("data") String data, String productName) throws InvalidDataException {

        Product p = productService.findByName(productName).get(0);

        Quid quid = quidService.findByProductId(p.getId()).get(0);

        quid.setDeclarationEditable(data);


        quidService.update(quid);

        return "redirect:calculate?id=" + p.getId();
    }


    private void bracketsHandler(List<QuidUtils> declaration, String productName, int count) {


        if (count < declaration.size() - 1) {


            setProductCompound(declaration, count, productName);

            setCurrentCompound(declaration, count);

            checkCurrentCompound(currentCompound, productCompound);

            addBrackets(declaration, productName, count);

        } else {


            addBracketsToLastElem(declaration, count);


        }


    }

    private void handleQuiddedAndDeclared(List<QuidUtils> quidded, List<QuidUtils> declaration, String productName, int count) {

        haveFunctionQuiddedAndDeclared(declaration, count);
        isAllergenQuiddedAndDEclared(declaration, count, productName);
        addQuid(quidded, declaration.get(count));

        bracketsHandler(declaration, productName, count);


    }

    private void handleOnlyDeclared(List<QuidUtils> declaration, String productName, int count) {


        haveFunctionOnlyDEclared(declaration, count);

        isAllergenOnlyDEclared(declaration, count);

        bracketsHandler(declaration, productName, count);


    }

    private void haveFunctionOnlyDEclared(List<QuidUtils> declaration, int count) {


        if (!"N/A".equals(declaration.get(count).getFunction())) {

            if (count > 0) {

                if (declaration.get(count).getFunction().equals(declaration.get(count - 1).getFunction())) {

                    this.declaration += "";


                } else this.declaration += " " + declaration.get(count).getFunction() + ": ";
            } else

                this.declaration += " " + declaration.get(count).getFunction() + ": ";

        }


    }

    private void haveFunctionQuiddedAndDeclared(List<QuidUtils> declaration, int count) {

        if (!"N/A".equals(declaration.get(count).getFunction())) {

            if (count > 0) {

                if (declaration.get(count).getFunction().equals(declaration.get(count - 1).getFunction())) {

                    if (this.declaration.endsWith("[")) {
                        this.declaration += " " + declaration.get(count).getFunction() + ": ";

                    } else

                        this.declaration += "";


                } else this.declaration += " " + declaration.get(count).getFunction() + ": ";
            } else

                this.declaration += " " + declaration.get(count).getFunction() + ": ";

        }


//        if (!"N/A".equals(declaration.get(count).getFunction())) {
//            this.declaration += " " + declaration.get(count).getFunction() + ": ";
//
//        }


    }

    private void isAllergenOnlyDEclared(List<QuidUtils> declaration, int count) {

        addAllergen(declaration.get(count).getDeclaredName(), declaration.get(count).getAllergen());

//        boolean res = true;
//
//        String[] restrictions = {"", "N/A", "COMPOUND"};
//
//        for (int i = 0; i < restrictions.length; i++) {
//
//            if (declaration.get(count).getAllergen().equals(restrictions[i])) {
//
//                res = false;
//
//            }
//        }
//
//        if (res == true) {
//
//            this.declaration += "<b> " + declaration.get(count).getDeclaredName().toLowerCase() + "</b>" + ",";
//
//        } else {
//
//            this.declaration += " " + declaration.get(count).getDeclaredName() + ",";
//        }
    }

    private void isAllergenQuiddedAndDEclared(List<QuidUtils> declaration, int count, String productName) {


        addAllergen(declaration.get(count).getDeclaredName(), declaration.get(count).getAllergen());


//        if (!declaration.get(count).getAllergen().equals("N/A")) {
//
//            this.declaration += "<b>" + declaration.get(count).getDeclaredName() + "</b>" + ",";
//
//        } else
//
//            this.declaration += " " + declaration.get(count).getDeclaredName() + ",";


    }

    private String isError(List<String> arrQuidNames, List<String> arrDeclaredNames) {

        String res = "";

        for (String quidName : arrQuidNames) {

            if (!arrDeclaredNames.contains(quidName)) {
                res = error;

            }
        }

        return res;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private void addBrackets(List<QuidUtils> declaration, String productName, int count) {


        if (productCompound != null) {

            if (productCompound.getDeclaredName().equals(declaration.get(count).getDeclaredName()) && declaration.get(count + 1).getOrder().startsWith(productCompound.getOrder())) {

                this.declaration = removeLastChar(this.declaration) + " [";

            }

        }

        if (productCompound != null) {


            if (!declaration.get(count + 1).getOrder().startsWith(productCompound.getOrder()))
                if (!declaration.get(count).getDeclaredName().equals(productCompound.getDeclaredName())) {

                    this.declaration = removeLastChar(this.declaration) + " ], ";


                }
        }

        if (currentCompound != null) {

            if (declaration.get(count).equals(currentCompound) && declaration.get(count + 1).getOrder().startsWith(currentCompound.getOrder())) {

                this.declaration = removeLastChar(this.declaration) + " (";

            }
        }

        if (currentCompound != null) {

            if (declaration.get(count).getParent().equals(currentCompound.getDeclaredName()) && !declaration.get(count + 1).getParent().equals(currentCompound.getDeclaredName())) {


                this.declaration = removeLastChar(this.declaration) + " ),";


            }

        }


    }


    private void addBracketsToLastElem(List<QuidUtils> declaration, int count) {


        this.declaration = removeLastChar(this.declaration);

        if (currentCompound != null) {

            if (!declaration.get(count).getDeclaredName().equals(currentCompound.getDeclaredName()) && declaration.get(count).getOrder().startsWith(currentCompound.getOrder()))

                this.declaration += " )";

        }


        if (productCompound != null) {

            if (declaration.get(count).getOrder().startsWith(productCompound.getOrder())) {


                this.declaration += " ]";

            }


        }
    }

    private void setProductCompound(List<QuidUtils> declaration, int count, String productName) {


        if (declaration.get(count).getParent().equals(productName)) {

            if (!declaration.get(count).getOrder().equals("1")) {

                productCompound = declaration.get(count);
            }

        }


    }

    private void setCurrentCompound(List<QuidUtils> declaration, int count) {


        if (productCompound != null) {

            if (declaration.get(count).getParent().equals(productCompound.getDeclaredName()) && declaration.get(count + 1).getParent().equals(declaration.get(count).getDeclaredName())) {

                currentCompound = declaration.get(count);

            }
        } else {

            if (declaration.get(count + 1).getParent().equals(declaration.get(count).getDeclaredName())) {

                currentCompound = declaration.get(count);
            }
        }

    }


    private boolean isQuidded(List<QuidUtils> quidded, String declaredName) {

        boolean res = false;
        for (QuidUtils q : quidded) {

            if (q.getQuidName().equals(declaredName)) {

                res = true;
            }

        }

        return res;
    }

    private void addQuid(List<QuidUtils> quidded, QuidUtils declared) {


        for (QuidUtils q : quidded) {

            if (q.getQuidName().equals(declared.getDeclaredName()) && q.getOrder().equals(declared.getOrder())) {


                declaration = removeLastChar(declaration) + " (" + roundResult(q.getQuidPerc()) + "%),";
            }

        }

    }

    private String roundResult(String perc) {

        if (Double.parseDouble(perc) > 5) {

            return String.valueOf(roundDown(Double.parseDouble(perc)));


        } else return String.valueOf(roundToHalf(Double.parseDouble(perc)));
    }


    private double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }

    private int roundDown(double d) {
        return (int) d;
    }


    private List<QuidUtils> quid(List<String> arrQuidNames, List<String> arrQuidPerc, List<String> arrQuidOrder) {

        List<QuidUtils> quidUtils = new ArrayList<QuidUtils>();

        for (int i = 0; i < arrQuidNames.size() && i < arrQuidPerc.size(); i++) {

            quidUtils.add(new QuidUtils(arrQuidNames.get(i), arrQuidPerc.get(i), arrQuidOrder.get(i)));

        }
        return quidUtils;
    }

    private List<QuidUtils> getDeclared(List<String> arrDeclaredNames, List<String> arrParent, List<String> arrOrder, List<String> arrFunctions, List<String> arrAllergens) {

        List<QuidUtils> declared = new ArrayList<QuidUtils>();

        for (int i = 0; i < arrDeclaredNames.size(); i++) {

            declared.add(new QuidUtils(arrDeclaredNames.get(i), arrParent.get(i), arrOrder.get(i), arrFunctions.get(i), arrAllergens.get(i)));

        }
        return declared;
    }

    private void checkCurrentCompound(QuidUtils currentCompound, QuidUtils productCompound) {

        if (!(currentCompound == null) && !(productCompound == null)) {

            if (!currentCompound.getOrder().startsWith(productCompound.getOrder())) {


                this.currentCompound = null;

            }
        }

    }


    private String listToString(List<String> list) {

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(",");
        }

        return String.valueOf(sb);
    }

    /* process Allergens*/

    private String[] splitIngredient(String ingredient) {

        if (ingredient.equalsIgnoreCase("Sulphur Dioxide")) {

            return new String[]{"Sulphur Dioxide"};
        } else

            return ingredient.split(" ");

    }

    private int getAllergenPosition(String allergen) {

        int pos = -1;

        for (int i = 0; i < WebappConstants.allergens.length; i++)
            if (WebappConstants.allergens[i].equals(allergen))

                pos = i;


        return pos;
    }

    private String arrayToString(String[] split) {

        String s = "";

        for (int i = 0; i < split.length; i++) {

            s += split[i] + " ";
        }

        return s;
    }

    private boolean containsAllergenName(String ingredient, String[] compare) {

        boolean res = false;

        for (int i = 0; i < compare.length; i++) {

            if (ingredient.toLowerCase().contains(compare[i].toLowerCase())) {
                res = true;

            }

        }
        return res;
    }

    private void addAllergen(String ingredient, String allergen) {


        boolean bold = false;


        if (!allergen.equals("N/A")) {

            int pos = getAllergenPosition(allergen);

            String[] split = splitIngredient(ingredient);

            //must be if contains
            if (containsAllergenName(ingredient, WebappConstants.allergens_compare[pos])) {

                for (int i = 0; i < split.length; i++) {

                    for (int j = 0; j < WebappConstants.allergens_compare[pos].length; j++) {

                        if (split[i].startsWith(WebappConstants.allergens_compare[pos][j])) {

                            split[i] = "<b>" + split[i] + "</b>";

                        }
                    }

                }
                declaration += arrayToString(split) + ",";

            } else {

                declaration += " " + ingredient + "(<b>" + WebappConstants.allergens_shorts[pos] + "</b>)" + ",";
            }

        } else {

            declaration += " " + ingredient + ",";
        }

    }

}