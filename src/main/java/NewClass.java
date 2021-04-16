
public class NewClass
{
    public static void main(String args[]){
        NewClass fib= new NewClass();
        int a=0;
        int b=1;
        int num=7;
        System.out.println(a);
        System.out.println(b);
        fib.fibonacci(a,b,num-2);


    }


    int fibonacci(int a, int b,int count) {
        int c=0;
        if (count > 0) {
            c = a + b;
            a = b;
            b = c;

            System.out.println(c);

            fibonacci(a, b,count-1);

        }

            return c;


        }
    }

