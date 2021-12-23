package com.twitter.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.twitter.Twitter;

@RunWith(JUnit4.class)
public class TwitterTest {
	
	static Twitter twitter;
	
	@BeforeClass
	public static void setUp() {
		twitter = new Twitter();
	}
	@Test
	public void testPostTweet() {
		int userId_1 = 1;
		int tweetId_1 = 1;
		
		int userId_2 = 2;
		int tweetId_2 = 2;
		
		twitter.postTweet(userId_1, tweetId_1);
		twitter.postTweet(userId_2, tweetId_2);
		
		twitter.follow(userId_1, userId_2);
		
		List<Integer> followers = twitter.getFollowers(userId_1);
		
		assertEquals(1, followers.size());
		
		int userId_3 = 3;
		int tweetId_3 = 3;
		
		twitter.postTweet(userId_3, tweetId_3);
		twitter.follow(userId_1, userId_3);
		
		followers = twitter.getFollowers(userId_1);
		assertEquals(2, followers.size());
	}

}
