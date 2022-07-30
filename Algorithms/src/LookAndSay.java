
public class LookAndSay {

	static String lookandsay(int n)
    {
        StringBuffer sbr1 = new StringBuffer("1");
        StringBuffer sbr2 = new StringBuffer("");
        char ch = 0;
        int count1=0, count2=0;
        if(n>1) {
            while(count1<n) {
            	System.out.println("#");
                for(int i=0;i<sbr1.length();i++) {
                	System.out.println("sbr1:"+sbr1);
                    if(i==0) {
                        count2++;
                    }
                    else {
                        if(sbr1.charAt(i) == sbr1.charAt(i-1)) {
                            count2++;
                        }
                        else {
                            sbr2.append(""+count2);
                            sbr2.append(sbr1.charAt(i));
                            count2=1;
                        }
                    }
                    ch = sbr1.charAt(i);
                }
                sbr2.append(""+count2);
                sbr2.append(String.valueOf(ch));
                sbr1.delete(0, sbr1.length());
                sbr1.append(sbr2);
                sbr2.delete(0, sbr2.length());
                count1++;
            }
            System.out.println(sbr1);
        }
        return sbr1.toString();
    }
	public static void main(String[] args) {
		System.out.println(lookandsay(3));
	}

}
