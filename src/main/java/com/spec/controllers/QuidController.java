package com.spec.controllers;

import com.spec.exceptions.InvalidDataException;
import com.spec.service.ComponentService;
import com.spec.utils.Quid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.spec.utils.WebappConstants.*;

/**
 * Created by Svetik on 21/12/2017.
 */
@Controller
public class QuidController extends AbstractController {

    private Quid productCompound;

    private Quid currentCompound;

    private String declaration;


    private final ComponentService componentService;

    @Autowired
    public QuidController(ComponentService componentService) {
        this.componentService = componentService;
    }


    @RequestMapping(value = "/parseQuid", method = RequestMethod.POST)
    public
    @ResponseBody
    String quid(@RequestBody Quid quid) throws ParseException, InvalidDataException {

        productCompound = null;
        currentCompound = null;
        declaration = "";

        String productName = quid.getProductName();


        List<String> arrQuidNames = quid.getArrQuidNames();

        List<Quid> quidded;
        List<Quid> declared;


            allQuid = quid.getAllQuid();

            allDeclared = quid.getAllDeclared();

            allAllergens = quid.getAllAllergens();

            allFunctions = quid.getAllFunctions();


        List<String> arrDeclaredNames = quid.getArrDeclaredNames();


        declaration = isError(arrQuidNames, arrDeclaredNames);


        if ("".equals(declaration)) {

            quidded = quid(quid.getArrQuidNames(), quid.getArrQuidPerc());

            declared = getDeclared(quid.getArrDeclaredNames(), quid.getArrParent(), quid.getArrOrder(), quid.getArrFunctions(), quid.getArrAllergens());

            for (int i = 0; i < declared.size(); i++) {

                if (isQuidded(quidded, declared.get(i).getDeclaredName())) {

                    handleQuiddedAndDeclared(quidded, declared, productName, i);
                } else {

                    handleOnlyDeclared(declared, productName, i);
                }


            }


        }

        declarationResult = declaration;
        return declaration;

    }


    private void bracketsHandler(List<Quid> declaration, String productName, int count) {


        if (count < declaration.size() - 1) {


            setProductCompound(declaration, count, productName);

            setCurrentCompound(declaration, count);

            checkCurrentCompound(currentCompound, productCompound);

            addBrackets(declaration, productName, count);

        } else {


            addBracketsToLastElem(declaration, count);


        }


    }

    private void handleQuiddedAndDeclared(List<Quid> quidded, List<Quid> declaration, String productName, int count) {

        haveFunctionQuiddedAndDeclared(declaration, count);
        isAllergenQuiddedAndDEclared(declaration, count);
        addQuid(quidded, declaration.get(count).getDeclaredName());

        bracketsHandler(declaration, productName, count);


    }

    private void handleOnlyDeclared(List<Quid> declaration, String productName, int count) {


        haveFunctionOnlyDEclared(declaration, count);

        isAllergenOnlyDEclared(declaration, count);

        bracketsHandler(declaration, productName, count);


    }

    private void haveFunctionOnlyDEclared(List<Quid> declaration, int count) {


        if (!"N/A".equals(declaration.get(count).getFunction())) {
            this.declaration += " " + declaration.get(count).getFunction() + ": ";

        }


    }

    private void haveFunctionQuiddedAndDeclared(List<Quid> declaration, int count) {


        if (!"N/A".equals(declaration.get(count).getFunction())) {
            this.declaration += " " + declaration.get(count).getFunction() + ": ";

        }


    }

    private void isAllergenOnlyDEclared(List<Quid> declaration, int count) {

        boolean res = true;

        String[] restrictions = {"", "N/A", "COMPOUND"};

        for (int i = 0; i < restrictions.length; i++) {

            if (declaration.get(count).getAllergen().equals(restrictions[i])) {

                res = false;

            }
        }

        if (res == true) {

            this.declaration += "<b> " + declaration.get(count).getDeclaredName().toLowerCase() + "</b>" + ",";

        } else {

            this.declaration += " " + declaration.get(count).getDeclaredName() + ",";
        }
    }

    private void isAllergenQuiddedAndDEclared(List<Quid> declaration, int count) {

        boolean res = true;

        String[] restrictions = {"", "N/A", "COMPOUND"};

        for (int i = 0; i < restrictions.length; i++) {

            if (declaration.get(count).getAllergen().equals(restrictions[i])) {

                res = false;

            }
        }

        if (res == true) {

            this.declaration += "<b>" + declaration.get(count).getDeclaredName().toUpperCase() + "</b>" + ",";

        } else {

            this.declaration += " " + declaration.get(count).getDeclaredName().toUpperCase() + ",";
        }
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

    private void addBrackets(List<Quid> declaration, String productName, int count) {


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


    private void addBracketsToLastElem(List<Quid> declaration, int count) {


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

    private void setProductCompound(List<Quid> declaration, int count, String productName) {


        if (declaration.get(count).getParent().equals(productName)) {

            if (!declaration.get(count).getOrder().equals("1")) {

                productCompound = declaration.get(count);
            }

        }


    }

    private void setCurrentCompound(List<Quid> declaration, int count) {


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


    private boolean isQuidded(List<Quid> quidded, String declaredName) {

        boolean res = false;
        for (Quid q : quidded) {

            if (q.getQuidName().equals(declaredName)) {

                res = true;
            }

        }

        return res;
    }

    private void addQuid(List<Quid> quidded, String declaredName) {


        for (Quid q : quidded) {

            if (q.getQuidName().equals(declaredName)) {


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
        return (int)d;
    }


    private List<Quid> quid(List<String> arrQuidNames, List<String> arrQuidPerc) {

        List<Quid> quid = new ArrayList<Quid>();

        for (int i = 0; i < arrQuidNames.size() && i < arrQuidPerc.size(); i++) {

            quid.add(new Quid(arrQuidNames.get(i), arrQuidPerc.get(i)));

        }
        return quid;
    }

    private List<Quid> getDeclared(List<String> arrDeclaredNames, List<String> arrParent, List<String> arrOrder, List<String> arrFunctions, List<String> arrAllergens) {

        List<Quid> declared = new ArrayList<Quid>();

        for (int i = 0; i < arrDeclaredNames.size(); i++) {

            declared.add(new Quid(arrDeclaredNames.get(i), arrParent.get(i), arrOrder.get(i), arrFunctions.get(i), arrAllergens.get(i)));

        }
        return declared;
    }

    private void checkCurrentCompound(Quid currentCompound, Quid productCompound) {

        if (!(currentCompound == null) && !(productCompound == null)) {

            if (!currentCompound.getOrder().startsWith(productCompound.getOrder())) {


                this.currentCompound = null;

            }
        }

    }


}