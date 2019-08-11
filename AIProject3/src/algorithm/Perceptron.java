package algorithm;

public class Perceptron {

  public Perceptron(){
    //this pseudo code assumes 3 features
    double w0=0, w1=0, w2=0, w3=0; //w0=bias term, w1 corresponds to feature 1, w2 corresponds to feature 2, w3 corresponds to feature 3
    int isOver=0;
    while(isOver==0){
      for each image{
         retrieve x1, x2, x3; //value of each feature for an image
         retrieve label;
         y=max(max(w0, w1*x1), max(w2*x2, w3*x3)); //get max of w0, w1, w2, w3
         isOver++;
         if(!y==label){
            isOver=0;
            w0=w0-label;
            if(w1*x1==label){
                w1=w1+y;
                w2=w2-y;
                w3=w3-y;
            }
            if(w2*x2==label){
                w1=w1-y;
                w2=w2+y;
                w3=w3-y;
            }
            if(w3*x3==label){
                w1=w1-y;
                w2=w2-y;
                w3=w3+y;
            }
         }
      }
      }


  }
}
