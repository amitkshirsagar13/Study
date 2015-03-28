package com.tutorialspoint;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.logging.Logger;

public class HelpMe {
	static Logger log = Logger.getLogger(HelpMe.class.getName());

	public static void main(String[] args) {
		System.out.println("This is the default class executed."
				+ "Please pass the fully qualified class"
				+ " name to be executed as command line"
				+ " parameter, for example," + " com.tutorialspoint.HelpMe ");
	}

}
