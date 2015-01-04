package com.sample.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commonj.timers.Timer;
import commonj.timers.TimerListener;
import commonj.timers.TimerManager;

public class TimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static long prev = 0L;
	public static long currentSampleTime = 0L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		try {
			InitialContext ic = new InitialContext();
			TimerManager tm = (TimerManager) ic
					.lookup("java:comp/env/tm/TimerManager");
			tm.scheduleAtFixedRate(new TestTimerListener(), 0, 10 * 1000);
			out.println("<h4>Timer scheduled! Check sysout</h4>");
		} catch (NamingException ne) {
			ne.printStackTrace();
			out.println("<h4>Timer schedule failed!</h4>");
		}
	}

	private static class TestTimerListener implements TimerListener {
		@Override
		public void timerExpired(Timer timer) {
			System.out.println("\n\n\t inside timerExpired()...");
			try {
				System.out
						.print("\n\t\t=====Write Your actual Code here, Which u want to execute After Every 10-Second=========");
				System.out.println("" + new java.util.Date());
				System.out.println("\n\t exiting timerExpired()...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
