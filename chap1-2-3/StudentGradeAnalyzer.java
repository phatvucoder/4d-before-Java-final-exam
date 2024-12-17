import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class StudentGradeAnalyzer {
    final static int A_GRADE_BOUNDARY = 90;
    final static int B_GRADE_BOUNDARY = 80;
    final static int C_GRADE_BOUNDARY = 70;
    final static int D_GRADE_BOUNDARY = 60;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Enter the number of students: ");
        int numStudents = input.nextInt();

        List<Student> students = new ArrayList();

        for(int i=0; i<numStudents; i++){
            System.out.print("name: ");
            input.nextLine();
            String name = input.nextLine();

            double score1;
            System.out.print("score1: ");
            while(true){
                score1 = input.nextDouble();
                if (isValidScore(score1))
                    break;
                else
                    System.out.print("Invalid. score1:");
            }

            double score2;
            System.out.print("score2: ");
            while(true){
                score2 = input.nextDouble();
                if (isValidScore(score2))
                    break;
                else
                    System.out.print("Invalid. score2:");
            }

            double score3;
            System.out.print("score3: ");
            while(true){
                score3 = input.nextDouble();
                if (isValidScore(score3))
                    break;
                else
                    System.out.print("Invalid. score3:");
            }

            int year;
            System.out.print("year: ");
            while(true){
                year = input.nextInt();
                if (year>0)
                    break;
                else
                    System.out.print("Invalid. year:");
            }

            students.add(new Student(name, score1, score2, score3, year));
        }

        System.out.printf("%-20s%-10s%-10s%-10s%-10s%-10s%-10s%-30s%n", 
                "Name", "Score 1", "Score 2", "Score 3", "Average", "Grade", "Year", "Message");

        double classAverage = 0;
        double highestScore = Double.MIN_VALUE;
        double lowestScore = Double.MAX_VALUE;

        for (Student student: students){
            double avr = student.calcAvr();
            classAverage += avr;
            if (avr > highestScore)
                highestScore = avr;
            if (avr < lowestScore)
                lowestScore = avr;

            String message = genRandomMess(r.nextInt(10));

            System.out.printf("%-20s%-10.2f%-10.2f%-10.2f%-10.2f%-10s%-10d%-30s%n",
                            student.getName(), student.getScore1(), student.getScore2(), student.getScore3(),
                            avr, student.getGrade(), student.getYear(), message + " " +  student.getNameLength());
        }
    }

    private static String genRandomMess(int randomNumber){
        String message;
        switch(randomNumber){
            case 0:
                message = "Excellent";
                break;
            case 1:
                message = "Good";
                break;
            case 2:
                message = "Average";
                break;
            case 3:
                message = "Pass";
                break;
            case 4:
                message = "Fail";
                break;
            case 5:
                message = "Study more";
                break;
            case 6:
                message = "Keep up the good work";
                break;
            case 7:
                message = "Doing great";
                break;
            case 8:
                message = "Well done";
                break;
            case 9:
                message = "Not bad";
                break;
            default:
                message = "Error";
                break;
        }

        return message;
    }

    private static boolean isValidScore (double score) {
        return (0<=score) && (score <=100);
    }
    
    static class Student {
        private String name;
        private double score1;
        private double score2;
        private double score3;
        private int year;

        Student(String name, double score1, double score2, double score3, int year) {
            this.name = name;
            this.score1 = score1;
            this.score2 = score2;
            this.score3 = score3;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public double getScore1() {
            return score1;
        }
        public double getScore2() {
            return score2;
        }
        public double getScore3() {
            return score3;
        }
        public int getYear() {
            return year;
        }
        public double calcAvr(){
            return (score1+score2+score3)/3;
        }
        public String getGrade(){
            double avr = calcAvr();
            String grade;

            if (avr >= A_GRADE_BOUNDARY) {
                grade = "A";
            } else if (avr >= B_GRADE_BOUNDARY) {
                grade = "B";
            } else if (avr >= C_GRADE_BOUNDARY) {
                grade = "C";
            } else if (avr >= D_GRADE_BOUNDARY) {
                grade = "D";
            } else {
                grade = "F";
            }
              return grade;
        }
        public String getNameLength(){
            if (this.name.length() % 2 == 0)
                return "Name has even letters";
            else
                return "Name has odd letters";
        }
    }
}