package com.twitter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Nagaraju Dirsipam
 *
 */
public class Twitter {
    private static LocalDateTime timeStamp;
    private HashMap<Integer, User> userRepository;

    public Twitter() {
    	userRepository = new HashMap<>();
    }

    /**
     * Add tweet to the list of tweets for the current user
     * @param userId the current user id
     * @param tweetId the tweet to be posted
     */
    public void postTweet(int userId, int tweetId) {
        if (!userRepository.containsKey(userId)) {
        	userRepository.put(userId, new User(userId));
        }
        userRepository.get(userId).post(tweetId);
    }

    /**
     * Make followerId to the currentUserId
     * @param currentUserId the current user id
     * @param followerId the follower user id
     */
    public void follow(int currentUserId, int followerId) {
    	// Check if currentUser exists, if not create a new one and assign follower
        if (!userRepository.containsKey(currentUserId)) {
            User user = new User(currentUserId);
            userRepository.put(currentUserId, user);
        }
        
     // Check if follower exists, if not create a new one and assign to the user
        if (!userRepository.containsKey(followerId)) {
            User user = new User(followerId);
            userRepository.put(followerId, user);
        }
        userRepository.get(currentUserId).follow(followerId);
    }
   
    /**
     * Fetch list of followers for the given user
     * @param userId the current user id
     * @return list of followers for given user
     */
    public List<Integer> getFollowers(int userId) {
        List<Integer> rst = new ArrayList<>();
        if (!userRepository.containsKey(userId)) {
            return rst;
        }
        rst.addAll(userRepository.get(userId).getFollowers());
        return rst;
    }
 
    class User {
        private int id;
        private Tweet tweet;
        private Set<Integer> followers;

        public Tweet getTweet() {
            return tweet;
        }

        public Set<Integer> getFollowers() {
            return followers;
        }

        User(int id) {
            this.id = id;
            followers = new HashSet<>();
            this.tweet = null;
        }

        /**
         * add id to the follower list
         * @param id the user id
         */
        public void follow(int id) {
        	followers.add(id);
        }

        /**
         * Create a new tweet
         * @param id the Tweet id
         */
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.nextTweet = tweet;
            tweet = t;
        }
    }

    private class Tweet {
        int id;
        LocalDateTime time;
        Tweet nextTweet;

        Tweet(int id) {
            this.id = id;
            time = LocalDateTime.now();
            nextTweet = null;
        }
    }
}