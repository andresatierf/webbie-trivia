package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.room.Room;
import org.academiadecodigo.altcatras65.util.ThemeType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionFactory {

    public static List<Question> createQuestions(ThemeType theme) {
        switch (theme) {
            case ALL:
                return createAllQuestions();
            case GENERAL:
                return createGeneralProgrammingQuestions();
            //case OOP:
            //case FUNCTIONAL:
            //case INTERNET:
            case WEB:
                return createWebQuestions();
            default:
                return createAllQuestions();
        }
    }

    private static List<Question> createWebQuestions() {
        LinkedList<Question> webQuestionsList = new LinkedList<Question>(Arrays.asList(
                new Question(
                        "What was the World Wide Web developed for?",
                        "Information sharing",
                        new String[]{
                                "Generating Income",
                                "Information sharing",
                                "To share cat videos",
                                "To look at porn"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "What year were the three fundamentals of the World Wide Web invented?",
                        "1989",
                        new String[]{
                                "1984",
                                "1991",
                                "1980",
                                "1989"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "Which are the three pillars of the WWW?",
                        "HTTP, URI, HTML",
                        new String[]{
                                "HTTP, URL, URI",
                                "URL, URI, HTML",
                                "HTTP, URI, HTML",
                                "JAVA, SOCKET, URL"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.WEB
                ),
                new Question(
                        "Who invented the World Wide Web?",
                        "Tim Berners Lee",
                        new String[]{
                                "Hulk Hogan",
                                "Steve Jobs",
                                "Ada Lovelace",
                                "Tim Berners Lee"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "URL, what is it?",
                        "A pointer to a resource",
                        new String[]{
                                "A HTML Document",
                                "A resource on the WWW",
                                "www.google.com",
                                "A pointer to a resource"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "What does HTTP stand for?",
                        "Hypertext Transfer Protocol",
                        new String[]{
                                "Hypertext Transfer Protocol",
                                "Hypertext Transmission Protocol",
                                "Hyper Response Protocol",
                                "Stateless Transfer protocol"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "How do you fetch an existing resource with HTTP Verbs?",
                        "GET",
                        new String[]{
                                "POST",
                                "PUT",
                                "DELETE",
                                "GET"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.WEB
                ),
                new Question(
                        "What does the HTTP Verb PUT do?",
                        "Updates a resource",
                        new String[]{
                                "Copies a resource",
                                "Deletes a resource",
                                "Creates a resource",
                                "Updates a resource"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "What does the HTTP Status Code 200 stand for?",
                        "OK",
                        new String[]{
                                "Gone",
                                "Bad Request",
                                "No",
                                "OK"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "The HTTP Status Code 400 stands for?",
                        "Bad Request",
                        new String[]{
                                "Forbidden",
                                "OK",
                                "Bad Request",
                                "Unauthorised"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "What are the 3 main Area Networks?",
                        "LAN, WAN, MAN",
                        new String[]{
                                "LAN, WLAN, WMAN",
                                "PAN, WAN, WLAN",
                                "PAN, LAN, WAN",
                                "LAN, WAN, MAN"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.WEB
                ),
                new Question(
                        "LAN stands for what exactly?",
                        "Local Area Network",
                        new String[]{
                                "Local Area Network",
                                "Large Area Network",
                                "Long Area Network",
                                "Long Distance Area Network"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "What does MAN stand for?",
                        "Metropolitan Area Network",
                        new String[]{
                                "Major Area Network",
                                "Mall Area Network",
                                "Man Area Network",
                                "Metropolitan Area Network"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "Who invented the Ethernet?",
                        "Xerox",
                        new String[]{
                                "Xerox",
                                "Microsoft",
                                "Apple",
                                "Hewlett-Packard"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "When was the Ethernet invented?",
                        "1970",
                        new String[]{
                                "1969",
                                "1989",
                                "1974",
                                "1970"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "What is the best commercially available ethernet cable available to us today?",
                        "Fiber optics cable",
                        new String[]{
                                "Fiber optics cable",
                                "Coaxial Cable",
                                "Ethernet cables",
                                "The router"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "What does the Ethernet Hub replace?",
                        "The coaxial bus",
                        new String[]{
                                "Fiber optics",
                                "Ethernet cables",
                                "The router",
                                "The coaxial bus"
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                ),
                new Question(
                        "What does an Ethernet Switch prevent?",
                        "Collisions",
                        new String[]{
                                "Collisions",
                                "Slow ethernet speeds",
                                "Getting hacked",
                                "Your computer has virus"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.WEB
                ),
                new Question(
                        "What Address is present on all ethernet network interfaces?",
                        "The MAC address",
                        new String[]{
                                "Uri",
                                "IP Address",
                                "URL",
                                "The MAC address"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                ),
                new Question(
                        "What does the Ethernet Frame contain?",
                        "The MAC address",
                        new String[]{
                                "The MAC address",
                                "Destination IP Address",
                                "UDP protocol",
                                "Source IP Address"
                        },
                        QuestionType.LOW,
                        ThemeType.WEB
                )
        ));

        LinkedList<Question> finalWebQuestionsList = new LinkedList<>();
        for (int i = 0; i < Room.DEFAULT_MAX_QUESTIONS; i++) {
            int rand = (int) (Math.random() * webQuestionsList.size());
            finalWebQuestionsList.add(webQuestionsList.get(rand));
            webQuestionsList.remove(rand);
        }

        return finalWebQuestionsList;
    }

    private static List<Question> createAllQuestions() {
        LinkedList<Question> questionsAll = new LinkedList<>();
        questionsAll.addAll(createWebQuestions());
        questionsAll.addAll(createGeneralProgrammingQuestions());

        LinkedList<Question> finalList = new LinkedList<>();
        for (int i = 0; i < Room.DEFAULT_MAX_QUESTIONS; i++) {
            int rand = (int) (Math.random() * questionsAll.size());
            finalList.add(questionsAll.get(rand));
            questionsAll.remove(rand);
        }
        return finalList;
    }

    private static List<Question> createGeneralProgrammingQuestions() {
        LinkedList<Question> generalProgrammingQuestionsList = new LinkedList<Question>(Arrays.asList(
                new Question(
                        "What is a variable?",
                        "Container",
                        new String[]{
                                "Container",
                                "It is a loop",
                                "It is a decision structure",
                                "Language"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.GENERAL
                ),
                new Question(
                        "What is the result of boolean result = 10<5;?",
                        "false",
                        new String[]{
                                "true",
                                "false",
                                "5",
                                "10"
                        },
                        QuestionType.HIGH,
                        ThemeType.GENERAL
                ),
                new Question(
                        "What is a Algorithm?",
                        "A set of instructions",
                        new String[]{
                                "A set of instructions",
                                "A fancy word",
                                "A problem",
                                "A sorter"
                        },
                        QuestionType.HIGH,
                        ThemeType.GENERAL
                ),
                new Question(
                        "Resolving errors in programming is called?",
                        "Debugging",
                        new String[]{
                                "Debugging",
                                "Problem solving",
                                "Error checking",
                                "Refixing"
                        },
                        QuestionType.LOW,
                        ThemeType.GENERAL
                ),
                new Question(
                        "Which of the following is not a high level programming language?",
                        "Assembly",
                        new String[]{
                                "Assembly",
                                "Java",
                                "C#",
                                "Kotlin"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.GENERAL
                ),
                new Question(
                        "What is a compiler?",
                        "Program",
                        new String[]{
                                "Program",
                                "Interpreter",
                                "Package",
                                "Hardware"
                        },
                        QuestionType.HIGH,
                        ThemeType.GENERAL
                ),
                new Question(
                        "What is a Interpreter?",
                        "Program",
                        new String[]{
                                "Program",
                                "Compiler",
                                "Analyzer",
                                "Network Protocol"
                        },
                        QuestionType.HIGH,
                        ThemeType.GENERAL
                ),
                new Question(
                        "Which numeral system can the machine understand?",
                        "Binary",
                        new String[]{
                                "Octal",
                                "Decimal",
                                "Binary",
                                "Hexa"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.GENERAL
                ),
                new Question(
                        "At run-time, a Java program is nothing more than objects ‘talking’ to?",
                        "Other objects",
                        new String[]{
                                "Other methods",
                                "Other classes",
                                "Other objects",
                                "Other binders"
                        },
                        QuestionType.LOW,
                        ThemeType.GENERAL
                ),
                new Question(
                        "Which of the following is the correct way of making a string in Java?",
                        "String text = \"text\";",
                        new String[]{
                                "String \"Text\";",
                                "String text = 'text';",
                                "String text = \"text\"",
                                "String text = \"text\";"
                        },
                        QuestionType.MEDIUM,
                        ThemeType.GENERAL
                )
        ));

        LinkedList<Question> generalProgrammingQuestionsListFinal = new LinkedList<>();
        for (int i = 0; i < Room.DEFAULT_MAX_QUESTIONS; i++) {
            int rand = (int) (Math.random() * generalProgrammingQuestionsList.size());
            generalProgrammingQuestionsListFinal.add(generalProgrammingQuestionsList.get(rand));
            generalProgrammingQuestionsList.remove(rand);
        }

        return generalProgrammingQuestionsListFinal;
    }
}
