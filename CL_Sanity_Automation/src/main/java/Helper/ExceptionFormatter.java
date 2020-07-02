package Helper;

import java.util.ArrayList;

public class ExceptionFormatter {
 public int FULL_STACK = -1;

 public ExceptionFormatter() {
 }

 public String format(String sMsg, Throwable e, Thread t) {
     ExceptionFormatter exceptionFormatter = new ExceptionFormatter();
     return this.format(sMsg, e, exceptionFormatter.FULL_STACK, t);
 }

 public String format(String sMsg, Throwable e) {
     ExceptionFormatter exceptionFormatter = new ExceptionFormatter();
     return this.format(sMsg, e, exceptionFormatter.FULL_STACK);
 }

 public void formattheException() {
     ArrayList<Integer> arrL = new ArrayList();
     arrL.add(1);
     arrL.add(2);
     arrL.add(3);
     arrL.add(4);
     arrL.forEach((n) -> {
         System.out.println();
     });
     arrL.forEach((n) -> {
         if (n % 2 == 0) {
             System.out.println();
         }

     });
 }

 public String format(String sMsg, Throwable e, int nStackDepth) {
     return this.format(sMsg, e, nStackDepth, Thread.currentThread());
 }

 public String format(String sMsg, Throwable e, int nStackDepth, Thread t) {
     StringBuilder sb = new StringBuilder();
     if (sMsg != null && sMsg.trim().length() > 0) {
         sb.append(sMsg).append('\n');
     }

     if (t != null) {
         sb.append("Thread: ").append(t.getName()).append('\n');
     }

     if (e != null) {
         sb.append(this.format(e, nStackDepth));
     }

     return sb.toString();
 }

 public String format(Throwable t) {
     if (t != null) {
         ExceptionFormatter exceptionFormatter = new ExceptionFormatter();
         return this.format(t, exceptionFormatter.FULL_STACK);
     } else {
         return null;
     }
 }

 public String format(Throwable t, int nStackDepth) {
     StringBuilder sb = new StringBuilder();
     int n = 1;

     for(Throwable e = t; e != null; e = e.getCause()) {
         if (n > 1) {
             sb.append('\n');
         }

         sb.append('[');
         sb.append(n);
         sb.append("] ");
         sb.append(e.getClass().getName());
         sb.append(": ");
         String sMsg = e.getMessage();
         if (sMsg != null) {
             if (sMsg.endsWith("\n")) {
                 sMsg = sMsg.substring(0, sMsg.length() - 1);
             }

             sb.append(sMsg);
         }

         StackTraceElement[] a_stack = e.getStackTrace();

         for(int i = 0; i < a_stack.length && (nStackDepth < 0 || i < nStackDepth); ++i) {
             sb.append("\n    at ");
             sb.append(a_stack[i].toString());
         }

         ++n;
     }

     return sb.toString();
 }

 public String formatFriendly(Throwable t) {
     StringBuilder sb = new StringBuilder();

     for(Throwable e = t; e != null; e = e.getCause()) {
         if (sb.length() > 0) {
             sb.append("\n  caused by: ");
         }

         String sMsg = e.getMessage();
         if (sMsg == null || sMsg.length() == 0) {
             sMsg = e.getClass().getSimpleName();
         }

         sb.append(sMsg);
     }

     return sb.toString();
 }

 public String toHtml(String s) {
     if (s != null) {
         StringBuilder sb = new StringBuilder(s.length() * 2);

         for(int i = 0; i < s.length(); ++i) {
             char ch = s.charAt(i);
             switch(ch) {
             case '\n':
                 sb.append("<br>");
                 break;
             case ' ':
                 sb.append("&nbsp;");
                 break;
             default:
                 sb.append(ch);
             }
         }

         return sb.toString();
     } else {
         return null;
     }
 }
}
