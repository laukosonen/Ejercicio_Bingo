import java.util.Scanner;

public class Ejercicio_Bingo {

    public static void main (String[] args){
    /*Defino las siguientes variables que necesitaré a lo largo del ejercicio*/

        int [] carton=new int[10];
        boolean repetido;
        int[] linea1;
        int[] linea2;
        int aleatorio;
        int[]bingo=new int[99];
        int contadorLinea1=0;
        int contadorLinea2=0;
        int contadorNumeros=0;

        /*Para generar el cartón, que se ha definido como un array de 10 posiciones  introduzco un for de 10
        repeticiones desde la posición 0 hasta la posición 9 en el que iré rellenando mi cartón con números
        aleatorios desde el 1 hasta el 99, ambis incluidos*/

        for (int i = 0; i < 10; i++)
        {
            carton[i]=(int) (Math.random()*99)+1;
        }

        /*Introduzco un DO WHILE para identificar los posibles números repetidos en mi array cartón. Para ello
        utilizo un FOR anidado dentro que me permita recorrer todas las posiciones del array; en caso de que se
        encuentre un número repetido, se generará un nuevo número aleatorio y se volverá a repetir todo el proceso*/

        do
        {
            repetido=false;
            for (int j = 0; j < 10; j++)
            {
                for (int i = 0; i < 10; i++)
                {
                   if(carton[i]==carton[j] && i!=j)
                   {
                    repetido=true;
                    carton[i]=(int)(Math.random()*99)+1;
                   }
                }
            }
        }while (repetido);

         /*Una vez verificado que no hay números repetidos en el carton imprimo el mismo en dos líneas.Se han generado
         dos arrays adicionales de 5 posiciones, uno por cada línea que utilizaré posteriormente*/


        System.out.println("Estos son los números de tu cartón:");

        System.out.print(carton[0]+ " "+carton[1]+" "+carton[2]+" "+carton[3]+" "+carton[4]);
        System.out.println();
        System.out.print(carton[5]+ " "+carton[6]+" "+carton[7]+" "+carton[8]+" "+carton[9]);
        System.out.println();

        linea1= new int [] {carton[0],carton[1],carton[2],carton[3],carton[4]};
        linea2= new int [] {carton[5],carton[6],carton[7],carton[8],carton[9]};

         /*Se le pide al usuario un número entero que será su apuesta dineraria. Si introduce un número negativo o
         igual a 0 saldrá un mensaje de error. También se le pide otro número en cuanto al número de "bolas" que
         tendrán que salir para cantar bingo. Si introduce un número inferior a 10 saldrá un mensaje de error*/

        Scanner scanner= new Scanner (System.in);
        System.out.println("Introduce tu apuesta:");
        int apuestaCantidad = scanner.nextInt();
        if(apuestaCantidad<0)
        {
            System.out.println("El número introducido no es válido");
        }
        System.out.println("¿En cuántos números crees que saldrá Bingo? Introduce un número:");
        int apuestaNumero = scanner.nextInt();
        System.out.println();
        if(apuestaNumero<10)
        {
            System.out.println("El número introducido no es válido");
        }

        /*Hemos declarado un array bingo de 99 posiciones, pues es el máximo de números que saldrán para cantar
        bingo. Su primera posición la vamos a rellenar aisladamente con un número aleatorio*/

        bingo[0]=(int)(Math.random()*99)+1;

        /*Introducimos un do while que nos permita ir generando números aleatorios desde la posición 1 de nuestro array
        bingo hasta un número indeterminado de veces. Cada número se comparará con todos los elementos que el array bingo
         vaya teniendo para, en caso de identificar un número repetido repetir el proceso completo. */

        do
        {
            do
            {
                    repetido=false;
                    aleatorio=(int)(Math.random()*99)+1;
                    for (int element:bingo)
                        {
                            if (aleatorio==element)
                            {
                                repetido=true;
                                aleatorio=(int)(Math.random()*99)+1;
                                break;
                            }
                        }
            }while(repetido);

            /*Cuando el número aleatorio no se ha encontrado en el array bingo, es decir, no está repetido, sumamos 1
            a nuestro contador y ese valor que va teniendo el contador será el que defina cada posición de mi array bingo
             en el cual se guardará el valor del número aleatorio correspondiente*/

            contadorNumeros++;
            bingo[contadorNumeros]=aleatorio;

            /*Recorremos con nuestro número aleatorio no repetido cada uno de los arrays de las líneas del cartón
            en caso de que se encuentre una coincidencia sumerá 1 al contador de la línea que corresponda. En el
             momento en el que se hayan encontrado las coincidencias para los 5 números de cada línea se imprimirá
             el mensanje "¡Línea!" y se imprimirá el número de "bolas" que se han necesitado para cantar línea*/

            for (int item:linea1)
            {
                if (aleatorio==item)
                {
                    contadorLinea1++;
                    if(contadorLinea1==5)
                    {
                        System.out.println("¡Línea!");
                        System.out.println("Ha salido línea en "+" "+contadorNumeros+" "+"números");
                        System.out.println();
                    }
                }
            }
            for (int item2:linea2)
            {
                if (aleatorio==item2)
                {
                    contadorLinea2++;
                    if(contadorLinea2==5)
                    {
                        System.out.println("¡Línea!");
                        System.out.println("Ha salido línea en "+" "+contadorNumeros+" "+"números");
                        System.out.println();
                    }
                }
            }

            /*Lo descrito hasta el momento desde la generación del cartón se ejecutará mientras alguno de los
            dos contadores de líneas sea distinto de cinco, pues eso querrá decir que aún no se han acertado las
            dos líneas del cartón y por tanto no habrá bingo*/

        }while (contadorLinea1!=5 || contadorLinea2!=5);

         /*En el momento en el que se hayan dado todas las coincidencias de todos los números aleatorios no repetidos
         con las dos líneas completas del cartón tendremos nuestro bingo, y entonces se imprimirá el mensaje "¡BINGO!
         y el número de "bolas" que han sido necearias para cantar bingo*/

        {
            System.out.println("¡BINGO!");
            System.out.println("Ha salido Bingo en "+" "+contadorNumeros+" "+"números");
            System.out.println();

            /*Comparando el contador de números con la apuesta que introdujo el usuario podremos saber si su apuesta
            está premiada, lo cual sólo ocurrirá si los dos números son iguales; en ese caso se imprimiré el mensaje
            de premio junto con el dinero ganado (dinero apostado*10). En caso contrario, se imprimirá el mensaje
             "Tu apuesta no ha sido premiada"*/
        if (contadorNumeros==apuestaNumero)
            {
                System.out.println("¡PREMIO! Has ganado "+apuestaCantidad*10+"€");
            }
            {
                System.out.println("Tu apuesta no ha sido premiada :(");
            }
        }
    }
}







