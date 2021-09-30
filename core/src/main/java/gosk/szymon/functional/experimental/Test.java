package gosk.szymon.functional.experimental;

import gosk.szymon.functional.Temporary;
import gosk.szymon.functional.structural.ValueBuilder;

import static gosk.szymon.functional.facades.Values.Decimal;
import static gosk.szymon.functional.facades.Values.Int;

public class Test {

    @Temporary
    public static void main(String[] args) {



//        ValueBuilder result = Int(2).add(Sqrt(Int(3))).divide(Sqrt(Int(5)).subtract(Int(3)));
//        ValueBuilder result2 = Frac(Int(2).add(Sqrt(Int(3))), Sqrt(Int(5)).subtract(Int(3)));
//        //ValueBuilder result = Int(2).add(Sqrt(Int(3)));
        //ValueBuilder simple = Frac(Int(8), Int(2));

//        System.out.println(
//                result.tree().equals(result2.tree())
//        );

        ValueBuilder result = Int(2).divide(Decimal(4.9).add(Decimal(2)));

        result.print();
        System.out.println(result);
//        System.out.println("HEIGHT: " + result.tree().height());
//        System.out.println("SIZE: " + result.tree().size());
//        System.out.println("ACTUAL SIZE: " + result.tree().reducePreOrder(result.tree().getRoot(), 0, (Node node, Integer i) -> i + 1));

//        result.print();
//        System.out.println("\n");
//        result2.print();

    }

}
