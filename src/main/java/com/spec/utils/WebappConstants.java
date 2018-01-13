package com.spec.utils;

import com.spec.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class WebappConstants {

    public static final String CURRENT_SESSION_ACCOUNT = "CURRENT_SESSION_ACCOUNT";

    public static final double PORK_PERCENTAGE = 100;

    public static final int ROLE_ADMIN = 1;

    public static final int ROLE_USER = 2;

    public static final int ACTIVE = 1;

    public static final int INACTIVE = 2;

    public static final String CONTEXT = "CONTEXT";

    public static List<Result> resultList;

    public static List<String> allQuid;

    public static List<String> allDeclared;

    public static List<String> allAllergens;

    public static List<String> allFunctions;

    public static String declarationResult;

    public static String total;

    public static String legalName;

    public static String error = "Error! All quids must be declared!";


    public static final String [] functions = {"N/A","Acid","Acidity Regulator","Anticaking Agent","Antifoaming Agent","Antioxidant","Bleaching Agent","Bulking agent","Carbonating Agent",
            "Carrier",
            "Chelating Agent",
            "Colour",
            "Colour retention Agent",

    "Emulsifier",
    "Emulsifying Salt",
    "Firming Agent",
    "Flavour Enhancer",
    "Flavouring",
    "Flour Treatment Agent",
    "Foaming Agent",
    "Gelling Agent",
    "Glazing Agent",
    "Humectant",
    "Modified Starch",
    "Mould Inhibitor",
    "Natural Colour",
    "Natural Flavouring",
    "Natural X Flavouring",
    "Natural X Flavouring With Other",
    "Packaging Gas",
            "Preservative",
    "Processing Aid",
    "Propellant",
    "Protective Ice Glaze",
    "Raising Agent",
    "Sequestrant",
    "Smoke Flavouring",
    "Stabilizer",
    "Statutory Additive",
    "Sweetener",
    "Thickener"};

    public static final String [] allergens = {"N/A","Peanuts","Nuts(other than peanuts)","Sesame Seeds and Derivatives","Celery and/or Celeriac and Derivatives",
    "Eggs and Eggs Derivatives","Fish and Fish Derivatives", "Lupin and Lupin Derivatives", "Milk and Milk Derivatives",
    "Cereals containing Gluten <=20ppm","Cereals containing Gluten >20ppm but <100ppm","Cereals containing Gluten >=100ppm",
    "Molluscs(gastropods,bivalves or cephalopods)","Mustard and Mustard Derivatives","Shellfish and Shellfish Derivatives(Crustaceans)",
    "Soy Beans and Soya Derivatives", "Sulphur Dioxide and Sulphites", "Risk of Cross Contamination of Peanuts","Risk of Cross Contamination of Nuts(other than peanuts)",
            "Risk of Cross Contamination of Sesame Seeds and Derivatives",
    "Risk of Cross Contamination of Celery and/or Celeriac and Derivatives",
    "Risk of Cross Contamination of Eggs and Eggs Derivatives",
            "Risk of Cross Contamination of Fish and Fish Derivatives",
    "Risk of Cross Contamination of Lupin and Lupin Derivatives",
            "Risk of Cross Contamination of Milk and Milk Derivatives",
            "Risk of Cross Contamination of Cereals containing Gluten <=20ppm",
            "Risk of Cross Contamination of Cereals containing Gluten >20ppm but <100ppm",
            "Risk of Cross Contamination of Cereals containing Gluten >=100ppm",
            "Risk of Cross Contamination of Molluscs(gastropods,bivalves or cephalopods)",
            "Risk of Cross Contamination of Mustard and Mustard Derivatives",
            "Risk of Cross Contamination of Shellfish and Shellfish Derivatives(Crustaceans)",
            "Risk of Cross Contamination of Shellfish and Shellfish Derivatives(Crustaceans)",
            "Risk of Cross Contamination of Soy Beans and Soya Derivatives",
            "Risk of Cross Contamination of Sulphur Dioxide and Sulphites"

    };


}
