package LldProblems;

import java.util.*;

/*
 * Actors:
 * PubSubSystem
 * Topic
 * Publisher
 * SubScriber
 */
public class PubSubDemo {
    public static void main(String[] args) {
        PubSubSystem pubSubSystem = PubSubSystem.getInstance();
        Publisher publisher1 = new Publisher("Publisher 1");
        Publisher publisher2 = new Publisher("Publisher 2");

        SubScriber subScriber1 = new SubScriber("Subscriber1");
        SubScriber subScriber2 = new SubScriber("Subscriber2");

        Topic topic1 = new Topic("Topic1");
        Topic topic2 = new Topic("Topic2");

        pubSubSystem.subScribe(topic1, subScriber1);
        pubSubSystem.subScribe(topic2, subScriber2);

        pubSubSystem.publish(publisher1, topic1, "Message for all the topic1 subscribers");
        pubSubSystem.publish(publisher2, topic2, "Message for all the topic2 subscribers");

    }
}

class PubSubSystem {
    private Map<Topic, List<SubScriber>> topicSubMap;
    private static PubSubSystem instance;

    private PubSubSystem() {
        topicSubMap = new HashMap<>();
    }

    public static PubSubSystem getInstance() {
        if (instance == null) {
            instance = new PubSubSystem();
        }
        return instance;
    }

    public void subScribe(Topic topic, SubScriber subScriber) {
        List<SubScriber> subScribers = topicSubMap.get(topic);
        if (subScribers != null) {
            subScribers.add(subScriber);
            topicSubMap.put(topic, subScribers);
        }else{
            subScribers = new ArrayList<>();
            subScribers.add(subScriber);
            topicSubMap.put(topic, subScribers);
        }
    }

    public void publish(Publisher publisher, Topic topic, String message) {
        publisher.publish(topic);
        List<SubScriber> subScribers = topicSubMap.get(topic);
        if (subScribers != null && !subScribers.isEmpty()) {
            for (SubScriber subScriber : subScribers) {
                subScriber.updateMessage(message);
            }
        } else {
            topicSubMap.put(topic, new ArrayList<>());
        }
    }

}

class Topic {
    private String content;

    public Topic(String content) {
        this.content = content;
    }

    public String getTopic() {
        return content;
    }
}

class Publisher {
    private String name;
    private Set<Topic> topics;

    public Publisher(String name) {
        this.name = name;
        topics = new HashSet<>();
    }

    public void publish(Topic topic) {
        topics.add(topic);
    }

    public String getPublisherName() {
        return name;
    }

}

class SubScriber {
    private String name;

    public SubScriber(String name) {
        this.name = name;
    }

    public void updateMessage(String message) {
        System.out.println("Sent message: " + message + " to " + name);
    }

}
