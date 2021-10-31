package org.academiadecodigo.altcatras65.game.question;

import org.academiadecodigo.altcatras65.game.ThemeType;

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
        return new LinkedList<Question>(Arrays.asList(
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
                                "Bad Request",
                                "OK",
                                "Forbidden",
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
                                "1970",
                                "1989",
                                "1974",
                                "1969"
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
                                "The MAC address",
                                "IP Address",
                                "URL",
                                "URI"
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
    }

    private static List<Question> createAllQuestions() {
        LinkedList<Question> questionsAll = new LinkedList<>();
        questionsAll.addAll(createWebQuestions());
        questionsAll.addAll(createGeneralProgrammingQuestions());
        return questionsAll;
    }

    private static List<Question> createGeneralProgrammingQuestions() {
        return new LinkedList<Question>(Arrays.asList(
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
                        ThemeType.WEB
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
                        ThemeType.WEB
                ),
                new Question(
                        "What is a Algorithm?",
                        "A set of instructions",
                        new String[]{
                                "A set of instructions",
                                "A fancy word",
                                "",
                                ""
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
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
                        ThemeType.WEB
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
                        ThemeType.WEB
                ),
                new Question(
                        "What is a compiler?",
                        "Program",
                        new String[]{
                                "Program",
                                "Interpreter",
                                "",
                                ""
                        },
                        QuestionType.HIGH,
                        ThemeType.WEB
                )
        ));
    }
}
