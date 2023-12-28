package inheritance.dogmonkey;

public class Monkey extends RescueAnimal {
    // Instance variable
private String species;
private String tailLength;
private String height;
private String bodyLength;


// Constructor
public Monkey(String name, String species, String gender, String age,
String weight, String height, String tailLength, String bodyLength, 
String acquisitionDate, String acquisitionCountry,
String trainingStatus, boolean reserved, String inServiceCountry) {
    setName(name);
    setSpecies(species);
    setGender(gender);
    setAge(age);
    setWeight(weight);
    setHeight(height);
    setTailLength(tailLength);
    setBodyLength(bodyLength);
    setAcquisitionDate(acquisitionDate);
    setAcquisitionLocation(acquisitionCountry);
    setTrainingStatus(trainingStatus);
    setReserved(reserved);
    setInServiceCountry(inServiceCountry);

}

/**
 * @return String return the species
 */
public String getSpecies() { //Accessor
    return species;
}

/**
 * @param species the species to set
 */
public void setSpecies(String species) { //Mutator
    this.species = species;
}

/**
 * @return String return the tailLength
 */
public String getTailLength() { //Accessor
    return tailLength;
}

/**
 * @param tailLength the tailLength to set
 */
public void setTailLength(String tailLength) { //Mutator
    this.tailLength = tailLength;
}

/**
 * @return String return the height
 */
public String getHeight() {   //Accessor
    return height;
}

/**
 * @param height the height to set
 */
public void setHeight(String height) { //Mutator
    this.height = height;
}

/**
 * @return String return the bodyLength
 */
public String getBodyLength() { //Accessor
    return bodyLength;
}

/**
 * @param bodyLength the bodyLength to set
 */
public void setBodyLength(String bodyLength) { //Mutator
    this.bodyLength = bodyLength;
}

}
