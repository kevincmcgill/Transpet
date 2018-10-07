package com.example.eunice.myapplication;

public class connect {

    public static int MLCal (double[] input) {

        //parameters
        double [][] fc1Weight = {
                { -0.233562514
                        , 0.073163345
                        , 0.162523463
                        , -0.475291908
                        , 0.153847501
                        , 0.250875354
                        , -0.159885272
                        , 0.325949907
                        , 0.007859653
                        , 0.097039938
                        , -0.161264479
                        , -0.205180123},

                { -0.30799529
                        , 0.031105876
                        , 0.127346873
                        , -0.564684391
                        , 0.077051081
                        , 0.169108436
                        , -0.327682018
                        , 0.358338505
                        , -0.019924065
                        , 0.106168672
                        , -0.206807643
                        , -0.206969202},

                { -0.329386055
                        , 0.097163714
                        , 0.127564043
                        , -0.512097239
                        , 0.154471964
                        , 0.191515937
                        , -0.258915633
                        , 0.2857714
                        , -0.052398041
                        , 0.153321788
                        , -0.155209497
                        , -0.183188558}
        };

        double[][] fc2Weight = {
                {0.368401468
                        ,0.411503017
                        ,0.390290767},
                {-0.358836472
                        , -0.406123072
                        , -0.384561062}
        };

        double [] fc1Bias = {-0.013109485
                ,-0.006122951
                ,0.001943798};
        double [] fc2Bias = {0.196922183,-0.196922451};
        //declare first layer output
        double[] fc1Output = {0,0,0};
        //first layer calculation
        for(int j = 0; j < 3; j ++) {
            for(int i = 0 ; i <input.length;i++) {
                fc1Output[j] = fc1Output[j] + fc1Weight[j][i]*input[i];
            }
            fc1Output[j] = fc1Output[j] +fc1Bias[j] ;
        }
        //declare second layer output
        double fc2Output [] = {0,0};
        //second layer calculation
        for(int j = 0; j < 2; j ++) {
            for(int i = 0 ; i <fc1Output.length;i++) {
                fc2Output[j] = fc2Output[j] + fc2Weight[j][i]*fc1Output[i];
            }
            fc2Output[j] = fc2Output[j] +fc2Bias[j] ;
        }

        //throw in second layer output and get softmax output
        double [] output  = softmax(fc2Output);

        //if the first one has larger probability return 0, else return 1
        if(output[0]>output[1]) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static double[] softmax (double [] input) {
        //declare output
        double [] output = new double [input.length];
        double sum = 0;
        //find the sum of the exponential of all numbers in input
        for(int i = 0 ;  i < input.length; i++) {
            sum = sum + Math.exp(input[i]);
        }
        //divide the input number by the sum
        for(int i = 0 ; i< output.length; i++) {
            output[i] = input[i]/sum;
        }
        //return output
        return output;
    }

}
