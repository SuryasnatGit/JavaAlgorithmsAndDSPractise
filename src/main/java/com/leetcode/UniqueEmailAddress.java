package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Every email consists of a local name and a domain name, separated by the @ sign.
 * 
 * For example, inalice@leetcode.com, aliceis the local name, andleetcode.comis the domain name.
 * 
 * Besides lowercase letters, these emails may contain'.'s or'+'s.
 * 
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be
 * forwarded to the same address without dots in the local name. For
 * example,"alice.z@leetcode.com"and"alicez@leetcode.com"forward to the same email address. (Note that this rule does
 * not apply for domain names.)
 * 
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain
 * emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com. (Again, this rule does not
 * apply for domain names.)
 * 
 * It is possible to use both of these rules at the same time.
 * 
 * Given a list ofemails, we send one email to each address in the list. How many different addresses actually receive
 * mails?
 * 
 * Example 1:
 * 
 * Copy
 * 
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 
 * Output: 2
 * 
 * Explanation: " testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 * 
 * Category : Easy
 */
public class UniqueEmailAddress {

	public int howManyUniqueEmails(List<String> emails) {
		Set<String> unique = new HashSet<>();

		for (String email : emails) {
			String localName = email.substring(0, email.indexOf("@"));
			localName = localName.substring(0, localName.indexOf("+"));
			localName = localName.replaceAll(".", "");
			String domainName = email.substring(email.indexOf("@"));
			unique.add(localName + "@" + domainName);
		}

		return unique.size();
	}

	public static void main(String[] args) {
		UniqueEmailAddress u = new UniqueEmailAddress();
		System.out.println(u.howManyUniqueEmails(Arrays.asList("test.email+alex@leetcode.com",
				"test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com")));
	}

}
