import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] array={"324234423","23","12","11","578","99","32","87","56","76","36","89","90","67","87","12","333","666",
        "123","323","313","767","342","3454353454354535345","6782353454354535345","645","123"};

      String[] result=sort3(array);

    for (int i=0;i< result.length;i++)
        System.out.println(result[i]);
     }


    public static String[] sort3(String[] array){
        //dzieli tabelę na podtabele na podstawie długości
        // Stringow w tabeli początkowej
        HashMap<Integer,String[]> map=new HashMap<>();
        //kluczem jest liczba a tablica w tym klucze to wszystkie Stringi o takiej długości
        for (String str:array            //dla każdego Stringa z tabeli początkowej
             ) {
            String[] strings={"q"};                 //robie tablice z jednego elementa jeżeli jeszcze nie bylo
                                                                            // Stringa o takiej długości w mapie
            if (map.get(str.length())!=null){
                strings=new String[map.get(str.length()).length+1];
                for(int i=0;i<map.get(str.length()).length;i++)
            strings[i]=map.get(str.length())[i];}                       //lub kopuje już istniejący w mapie

            strings[strings.length-1]=str;          //dodaje do tablicy String
            map.put(str.length(),strings);          //a następnie dodaje wynikową tabelę do mapy

        }



        for (Integer i: map.keySet()
        ) {                                          //dla każdej napotkanej długości Stringa w tabeli początkowej
            map.put(i,sort4(map.get(i),i-1));         //sortuje stringi ten samej długości tak, jakby były liczbami
        }


        Integer j=1;    //j to długośc Stringa
        int t=0;            //t to numer elementa w tablice z mapy
        for (int i=0; i<array.length;i++){      //zastępuje elementy tabeli początkowej elementami
                                                                            // z tabel uzyskanych podczas sortowania

            while (map.get(j)==null)            //wyszukuje długości Stringow
                j++;

            array[i]=map.get(j)[t];
            t++;
            if(t==map.get(j).length){       //jeśli wszystkie Stringi o takiej długości zostały dodany zwiększa t
                t=0;
                j++;
            }
        }
        return array;
    }




    public static String[] sort4(String[] array,int l){ //sortuje stringi ten samej długości tak, jakby były liczbami
                            //l jest to pozycja, według której są sortowane
        if (l==-1)
            return array;   //znaczy  że posortowanie

        int[] C=new int[10];       //tablica od 0 do 9
        String[] B=new String[array.length];    //tablica zynikami
        for (int i=0;i<10;i++){
            C[0]=0;
        }
        for (int j=0;j< array.length;j++){      //dla każdej z 10 cyfr pokazuje, ile razy ta
                                                    // cyfra występuje w danej tabeli w miejscu l

            C[(int) array[j].charAt(l)-48]++;       //odejmuje od elementa 48,bo char '0'==48
        }
        for (int i=1;i<10;i++) {
            C[i] = C[i] + C[i - 1];     //do każdego elementa tablicy С dodaje sumę elementów mniejszych od тупщ

        }

        for (int i= array.length-1;i>=0;i--){

           B[C[(int) array[i].charAt(l)-48]-1]=array[i];   //stawi na miejsce otrzymane w tablice C element z podanej tablicy
                                                                        // odejmuje 1,bo tablica zaczyna od 0 a ne od 1
           C[(int) array[i].charAt(l)-48]--;        //tak aby następna liczba, która ma tę samą wartość zamiast l,
                                                            // nie zastąpiła właśnie podanej liczby
        }return sort4(B,l-1);      //robi sort dla pozycji o 1 mniejszej niż teraz
    }

}