package assign02;

import java.util.ArrayList;

/**
 * This class represents a CS2420 student, which extends UofUStudent
 * @author Isaac Hall, Bradley Bartelt
 * @version January 24, 2023
 */

public class CS2420Student extends UofUStudent{
    private EmailAddress contactInfo;
    private ArrayList<Double> quizzes;
    private ArrayList<Double> labs;
    private ArrayList<Double> exams;
    private ArrayList<Double> assignments;
    /**
     * Creates a student from the given first name, last name, and uNID.
     *
     * @param firstName
     * @param lastName
     * @param uNID
     */
    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
        exams = new ArrayList<>();
        quizzes = new ArrayList<>();
        assignments = new ArrayList<>();
        labs = new ArrayList<>();
    }

    /**
     * returns the contact information of a student
      * @return contactInfo
     */
    public EmailAddress getContactInfo(){
        return contactInfo;
    }

    /**
     * adds scores to given category
     * @param score
     * @param category
     */
    public void addScore(double score, String category){

        switch(category) {
            case "quiz":
                quizzes.add(score);
                break;
            case "exam":
                exams.add(score);
                break;
            case "assignment":
                assignments.add(score);
                break;
            case "lab":
                labs.add(score);
                break;
        }
    }

    /**
     * returns a double value of the students final score in the class
     * @return double
     */
    public double computeFinalScore(){
        double quizScore = 0;
        double labScore = 0;
        double examScore = 0;
        double assignmentScore = 0;
        for(Double d: quizzes){
            quizScore += d;
        }
        for(Double d: labs){
            labScore += d;
        }
        for(Double d: exams){
            examScore += d;
        }
        for(Double d: assignments){
            assignmentScore += d;
        }
        if(labScore == 0 || quizScore == 0 || assignmentScore == 0 || examScore == 0){
            return 0.0;
}
        if(examScore/(exams.size())  < 65) {
            return examScore / (exams.size());
        }

        return (labScore/(labs.size()) * .1) + (examScore/(exams.size()) * .4) + (quizScore/(quizzes.size()) * .1) + (assignmentScore/(assignments.size()) * .4);
    }

    /**
     * returns the student's final letter grade in the class
     * @return String
     */
    public String computeFinalGrade(){
        double finalPercent = computeFinalScore();
        if(finalPercent == 0.0)
            return "N/A";
        if(finalPercent >= 93 && finalPercent <= 100){
            return "A";
        }
        else if(finalPercent >= 90 && finalPercent < 93){
            return "A-";
        }
        else if(finalPercent >= 87 && finalPercent < 90){
            return "B+";
        }
        else if(finalPercent >= 83 && finalPercent < 87){
            return "B";
        }
        else if(finalPercent >= 80 && finalPercent < 83){
            return "B-";
        }
        else if(finalPercent >= 77 && finalPercent < 80){
            return "C+";
        }
        else if(finalPercent >= 73 && finalPercent < 77){
            return "C";
        }
        else if(finalPercent >= 70 && finalPercent < 73){
            return "C-";
        }
        else if(finalPercent >= 67 && finalPercent < 70){
            return "D+";
        }
        else if(finalPercent >= 63 && finalPercent < 67){
            return "D";
        }
        else if(finalPercent >= 60 && finalPercent < 63){
            return "D-";
        }
        else if(finalPercent < 60){
            return "E";
        }
        return "";
    }
}
