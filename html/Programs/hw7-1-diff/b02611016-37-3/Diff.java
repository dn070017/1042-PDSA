
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
      this.cards = cards;
        return;
    }

    // TODO
    public int compareTo(Hand that) {
        int [] this_face=new int[15];
        int [] that_face=new int[15];
        int this_round1=1;
        int that_round1=1;
        int this_round2=0;
        int that_round2=0;
        int this_round3=0;
        int that_round3=0;
        for(int i=0;i<5;i++)
        {
            if(this.cards[i].getFace().equals(""A"")) { this_face[14]=this_face[14]+1;} 
            else if(this.cards[i].getFace().equals(""K"")){this_face[13]=this_face[13]+1;}
            else if(this.cards[i].getFace().equals(""Q"")){this_face[12]=this_face[12]+1;}
            else if(this.cards[i].getFace().equals(""J"")){this_face[11]=this_face[11]+1;}
            else{this_face[Integer.parseInt(this.cards[i].getFace())]=this_face[Integer.parseInt(this.cards[i].getFace())]+1;}
            
        }
     //   for(int i=2;i<15;i++)
      //  {System.out.println(this_face[i]);}
        for(int i=0;i<5;i++)
        {
            if(that.cards[i].getFace().equals(""A"")) { that_face[14]=that_face[14]+1;} 
            else if(that.cards[i].getFace().equals(""K"")){that_face[13]=that_face[13]+1;}
            else if(that.cards[i].getFace().equals(""Q"")){that_face[12]=that_face[12]+1;}
            else if(that.cards[i].getFace().equals(""J"")){that_face[11]=that_face[11]+1;}
            else{that_face[Integer.parseInt(that.cards[i].getFace())]=that_face[Integer.parseInt(that.cards[i].getFace())]+1;}
        }
   //      for(int i=2;i<15;i++)
    //    {System.out.println(that_face[i]);}
  search: for(int j=2;j<15;j++)
        { if(this_face[j]>=2){
            if(this_face[j]==3){for(int k=j+1;k<15;k++)
              {if(this_face[k]==2){this_round1=6;this_round2=j; break search;}
            
              }
             this_round1=2;this_round2=j;
            }
            else{for(int k=j+1;k<15;k++)
              {if(this_face[k]==3){this_round1=6;this_round2=k; break search;}
               else if(this_face[k]==2){this_round1=3;this_round2=k; break search;}
               
              }
             this_round1=2;this_round2=j;
            }
          }
        }
  
 search1: for(int j=2;j<15;j++)
        { if(that_face[j]>=2){
            if(that_face[j]==3){for(int k=j+1;k<15;k++)
              {if(that_face[k]==2){that_round1=6;that_round2=j; break search1;}
              
              }
            that_round1=2;that_round2=j;
            }
            else{for(int k=j+1;k<15;k++)
              {if(that_face[k]==3){that_round1=6;that_round2=k; break search1;}
               else if(that_face[k]==2){that_round1=3;that_round2=k; break search1;}
               
              }
            that_round1=2;that_round2=j;
            }
          }
        }
 search2:for(int j=2;j<11;j++)
        { if(j==2&&this_face[j]==1)
          {if(this_face[14]==1){if(this_face[3]==1&&this_face[4]==1&&this_face[5]==1){this_round1=4;this_round2=5;break search2;}}
           if(this_face[3]==1&&this_face[4]==1&&this_face[5]==1&&this_face[6]==1){this_round1=4;this_round2=6;break search2;}
          }
         else if(j!=2&&this_face[j]==1){if(this_face[j+1]==1&&this_face[j+2]==1&&this_face[j+3]==1&&this_face[j+4]==1){this_round1=4;this_round2=j+4;break search2;}}
        }
 search3:for(int j=2;j<11;j++)
        { if(j==2&&that_face[j]==1)
          {if(that_face[14]==1){if(that_face[3]==1&&that_face[4]==1&&that_face[5]==1){that_round1=4;that_round2=5;break search3;}}
           if(that_face[3]==1&&that_face[4]==1&&that_face[5]==1&&that_face[6]==1){that_round1=4;this_round2=6;break search3;}
          }
         else if(j!=2&&that_face[j]==1){if(that_face[j+1]==1&&that_face[j+2]==1&&that_face[j+3]==1&&that_face[j+4]==1){that_round1=4;that_round2=j+4;break search3;}}
        }
 search4:
         if(this.cards[0].getSuit().equals(this.cards[1].getSuit())&&this.cards[0].getSuit().equals(this.cards[2].getSuit())&&this.cards[0].getSuit().equals(this.cards[3].getSuit())&&this.cards[0].getSuit().equals(this.cards[4].getSuit()))
         {this_round1=5;}
         if(that.cards[0].getSuit().equals(that.cards[1].getSuit())&&that.cards[0].getSuit().equals(that.cards[2].getSuit())&&that.cards[0].getSuit().equals(that.cards[3].getSuit())&&that.cards[0].getSuit().equals(that.cards[4].getSuit()))
         {that_round1=5;}
.
     //if(this.cards[1].getFace().equals(""9"")) {return 1;} 
     //    System.out.println(this_round1);
    //     System.out.println(that_round1);
         
        if(this_round1>that_round1){return 1;}
        if(this_round1<that_round1){return -1;}
        if(this_round1==that_round1&&this_round1==6)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
          }
        if(this_round1==that_round1&&this_round1==4)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          } 
        if(this_round1==that_round1&&this_round1==5)
          {if(this.cards[0].getFace().equals(""Spades"")) { this_round2=4;} 
           else if(this.cards[0].getSuit().equals(""Hearts"")){this_round2=3;}
           else if(this.cards[0].getSuit().equals(""Diamonds"")){this_round2=2;}
           else if(this.cards[0].getSuit().equals(""Clubs"")){this_round2=1;}
           if(that.cards[0].getFace().equals(""Spades"")) { that_round2=4;} 
           if(that.cards[0].getSuit().equals(""Hearts"")){that_round2=3;}
           if(that.cards[0].getSuit().equals(""Diamonds"")){that_round2=2;}
           if(that.cards[0].getSuit().equals(""Clubs"")){that_round2=1;}
           if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {for(int k=14;k>1;k--)
                 {if(this_face[k]==1){this_round3=k;break;}
                 }
              for(int k=14;k>1;k--)
                 {if(that_face[k]==1){that_round3=k;break;}
                 }
              if(this_round3>that_round3){return 1;}
              if(this_round3<that_round3){return -1;}
             } 
          }
        if(this_round1==that_round1&&this_round1==3)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
            else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          }
        if(this_round1==that_round1&&this_round1==2)    
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
            else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          }
        if(this_round1==that_round1&&this_round1==1)    
          {for(int k=14;k>1;k--)
                 {if(this_face[k]==1){this_round2=k;break;}
                 }
              for(int k=14;k>1;k--)
                 {if(that_face[k]==1){that_round2=k;break;}
                 }
               if(this_round2>that_round2){return 1;}
               if(this_round2<that_round2){return -1;}
               if(this_round2==that_round2)
                 {if(this_round2==14)
                    {for(int i=0;i<5;i++)
                        {if(this.cards[i].getFace().equals(""A""))
                           {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                            else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                            else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                            else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                           }
                         if(that.cards[i].getFace().equals(""A""))
                            {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                             else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                             else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                             else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                            }
                        }
                    }
                 else if(this_round2==13)
                     {for(int i=0;i<5;i++)
                         {if(this.cards[i].getFace().equals(""K""))
                            {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                             else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                             else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                             else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                            }
                          if(that.cards[i].getFace().equals(""K""))
                            {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                             else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                             else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                             else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                            }
                        }
                     }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
                 }
           
           }
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
    
    public static void main(String[] args) throws Exception{

      /*  try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }

            Arrays.sort(playerArray);
            System.out.println(playerArray[playerCount - 1].getName());
        }*/
    }
}

