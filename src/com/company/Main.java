package com.company;

import jdk.dynalink.NamedOperation;

import java.io.*;
import java.util.Scanner;

class Contacts
{
    // Single Linked List used to store contacts with some basic functions to add,delete and to search
    static int balance=0;
    SearchHistory sh=new SearchHistory();
    Trash t1=new Trash();
    class Node
    {
        int NumberData;
        String NameData;
        String tag ;
        String favourite ;
        Node next;
    }
    Node head=null;
    void AddContact(int Con_number,String Con_name,String tags,String favourite)
    {
        Node newnode=new Node();
        newnode.NumberData= Con_number;
        newnode.NameData = Con_name;
        newnode.tag = tags;
        newnode.favourite = favourite;
        if(head==null){
            head=newnode;
            return;
        }
        Node curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=newnode;
        newnode.next=null;
    }
    //These two function below will delete a contact upon the search of a name
    void DeleteByName(Node ptr,Node curr,int temp){
        if(temp==0){
            head=curr.next;
            curr=null;
        }else{
            ptr.next= curr.next;
            curr.next=null;
        }
    }
    void SearchForDeletionOnly(String data,int numberdata)
    {
        int temp =0 ;
        int count=0;
        Node curr=head;
        Node ptr=null;
        while(curr != null)
        {
            if(curr.NameData.equals(data) && curr.NumberData==numberdata)
            {   count++;
                System.out.println("Contact Deleted!");
                DeleteByName(ptr,curr,temp);
                t1.Add(numberdata,data, curr.tag, curr.favourite);
                break;
            }
            ptr = curr;
            curr=curr.next;
            temp++;
        }
        if(count==0) {
            System.out.println("Contact Doesn't Exists");
        }
    }
    //Traversing through the LinkedList for editing purposes
    void TraversingFunctionForEditName(String data,int numberdata,String Name)
    {
        int temp =0 ;
        int count=0;
        Node curr=head;
        while(curr != null)
        {
            if(curr.NameData.equals(data) && curr.NumberData==numberdata)
            {   count++;
                EditName(curr,Name);
                break;
            }
            curr=curr.next;
            temp++;
        }
        if(count==0){
            System.out.println("Contact Doesn't Exists");
        }
    }
    void TraversingFunctionForEditNumber(String data,int numberdata,int Number)
    {
        int temp =0 ;
        int count=0;
        Node curr=head;
        while(curr != null)
        {
            if(curr.NameData.equals(data) && curr.NumberData==numberdata)
            {   count++;
                EditNumber(curr,Number);
                break;
            }
            curr=curr.next;
            temp++;
        }
        if(count==0){
            System.out.println("Contact Doesn't Exists");
        }
    }
    //Search function find any contact
    void IterationForSearchFunction(String data)
    {
        int temp =0 ;
        int count=0;
        Node curr=head;
        while(curr != null)
        {
            if(curr.NameData.equals(data))
            {
                count++;
                sh.Add(curr.NumberData,curr.NameData);
                System.out.println(curr.NameData+"====>"+curr.NumberData+"====>"+curr.tag);
            }
            curr=curr.next;
            temp++;
        }
        if(count==0){
            System.out.println("Contact Doesn't Exists!");
        }
    }
    int TotalLength(){
        int count=0;
        Node curr=head;
        while(curr!=null){
            curr=curr.next;
            count++;
        }
        return count;
    }
    //These 2 functions below check if the contact added is a duplicate or not
    int DuplicateCheckerByNumbers(int data){
        Node curr=head;
        int count=0;
        int starter=0;
        while(starter<TotalLength()){
            if(curr.NumberData==data){
                count++;
            }
            starter++;
            curr=curr.next;
        }
        return count;
    }
    int DuplicateCheckerByNames(String data){
        Node curr=head;
        int count=0;
        int starter=0;
        while(starter<TotalLength()){
            if(curr.NameData.equals(data)){
                count++;
            }
            starter++;
            curr=curr.next;
        }
        return count;
    }
    void EditName(Node node, String name)
    {
        node.NameData=name;
    }
    void EditNumber(Node node,int number)
    {
        node.NumberData=number;
    }
    //Ascending Order Sorting
    void SortedDisplayFunctionA_Z()
    {
        Node i ;
        Node j;
        String temp;
        int temp1 ;
        for(i =head; i.next!=null; i = i.next)
        {
            for(j = i.next; j !=null; j = j.next)
            {
                if(i.NameData.compareTo(j.NameData) > 0){
                    temp= i.NameData;
                    i.NameData= j.NameData;
                    j.NameData=temp;
                    temp= i.tag;
                    i.tag= j.tag;
                    j.tag=temp;
                    temp= i.favourite;
                    i.favourite= j.favourite;
                    j.favourite=temp;
                    temp1= i.NumberData;
                    i.NumberData = j.NumberData;
                    j.NumberData = temp1 ;
                }
            }
        }
    }
    //Descending Order Sorting
    void SortedDisplayFunctionZ_A()
    {
        Node i ;
        Node j;
        String temp;
        int temp1 ;
        for(i =head; i.next!=null; i = i.next)
        {
            for(j = i.next; j !=null; j = j.next)
            {
                if(j.NameData.compareTo(i.NameData) > 0){
                    temp= i.NameData;
                    i.NameData= j.NameData;
                    j.NameData=temp;
                    temp= i.tag;
                    i.tag= j.tag;
                    j.tag=temp;
                    temp= i.favourite;
                    i.favourite= j.favourite;
                    j.favourite=temp;
                    temp1= i.NumberData;
                    i.NumberData = j.NumberData;
                    j.NumberData = temp1 ;
                }
            }
        }
    }
    void display(String a)
    {
        if(a.equals("A"))
        {
            SortedDisplayFunctionA_Z();
        }
        else if(a.equals("Z"))
        {
            SortedDisplayFunctionZ_A();
        }
        Node curr = head ;

        while (curr!=null)
        {
            System.out.print(curr.NameData+"===>");
            System.out.print(curr.NumberData);
            System.out.println();
            curr = curr.next;
        }
        System.out.println();
    }
    String NameGiver(int number){
        Node curr=head;
        int count=0;
        int check=TotalLength();
        while (curr != null && curr.NumberData != number) {
            curr = curr.next;
            count++;
        }
        if(count==TotalLength()){
            return null;
        }else {
            return curr.NameData;
        }
    }
    void Display(int a,String b)
    {
        Node curr=head;
        while(curr!=null)
        {
            if(a==1)
            {
                System.out.print(curr.NameData+"===>");
                System.out.print(curr.NumberData+"===>");
                System.out.println(curr.tag);
            }
            else if(a==2)
            {
                if(b.equals("A"))
                {
                    display("A");
                }
                else if(b.equals("Z"))
                {
                    display("Z");
                }
                break;
            }
            else if(a==3)
            {
                if(curr.tag.equals(b))
                {
                    System.out.print(curr.NameData+"===>");
                    System.out.print(curr.NumberData+"===>");
                    System.out.println(curr.tag);
                }
            }
            else if(a==4)
            {
                if(curr.favourite.equals(b))
                {
                    System.out.print(curr.NameData+"===>");
                    System.out.print(curr.NumberData+"===>");
                    System.out.println(curr.favourite);
                }
            }
            curr=curr.next;
        }
        System.out.println("Empty!");
    }
    void Trashdisplay(){
        t1.TrashDisplay();
    }
    void SearchDisplay(){
        sh.SearchHistoryDisplay();
    }
    void revieve(int a, String b)
    {
        Trash.Node temp = t1.Restore(a,b);
        AddContact(temp.DeletedNumber, temp.DeletedNumbersNames,temp.Deletedtag,temp.Deletedfavourite);
    }
    void BalanceRecharge(int x){
        balance=balance+x;
    }
    int BalanceCheck(){
        return balance;
    }
}
class Trash
{
    //Basically another Linked List is being created to store the deleted contacts from the orignal LinkedList so if you want to restore or diplay trash contacts , you can do it from the functions used below
    class Node
    {
        int DeletedNumber;
        String DeletedNumbersNames;
        String Deletedtag;
        String Deletedfavourite;
        Node next;
    }
    Node head=null;
    void Add(int DeletedNumber,String DeletedNumbersNames,String Deletedtag,String Deletedfavourite)
    {
        Node newnode=new Node();
        newnode.DeletedNumber= DeletedNumber;
        newnode.DeletedNumbersNames = DeletedNumbersNames;
        newnode.Deletedtag = Deletedtag;
        newnode.Deletedfavourite = Deletedfavourite;
        if(head==null){
            head=newnode;
            return;
        }
        Node curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=newnode;
        newnode.next=null;
    }
    void TrashDisplay(){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.DeletedNumbersNames+"===>");
            System.out.print(curr.DeletedNumber+"===>");
            System.out.print(curr.Deletedtag+"===>");
            System.out.print(curr.Deletedfavourite);
            System.out.println();
            curr=curr.next;
        }
        System.out.println("Empty!");
    }
    Node Restore(int a, String b)
    {
        int temp1=0;
        int count=0;
        Node curr = head;
        Node ptr=new Node();
        Node temp = new Node();
        while(curr != null)
        {
            if(curr.DeletedNumbersNames.equals(b) && curr.DeletedNumber==a)
            {   count++;
                //ptr.next=curr.next;
                DeleteByName(ptr,curr,temp1);
                temp = curr;
                // curr.next=null;
                break;
            }
            ptr = curr;
            curr=curr.next;
            temp1++;
        }
        if(count==0){
            System.out.println("The Contact Doesn't Exists");
        }
        return temp;
    }
    void DeleteByName(Node ptr, Node curr, int temp){
        if(temp==0){
            head=curr.next;
            curr=null;
        }else{
            ptr.next= curr.next;
            curr.next=null;
        }
    }
}
class SearchHistory
{
    //Another LL is there for search history as the amount of data being searched in the original LL will also be added in this LL
    class Node{
        int Number;
        String Names;
        Node next;
    }
    Node head=null;
    void Add(int Con_number,String Con_name)
    {
        Node newnode=new Node();
        newnode.Number= Con_number;
        newnode.Names = Con_name;
        if(head==null){
            head=newnode;
            return;
        }
        Node curr=head;
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=newnode;
        newnode.next=null;
    }
    void SearchHistoryDisplay(){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.Names+"===>");
            System.out.print(curr.Number);
            System.out.println();
            curr=curr.next;
        }
        System.out.println("Empty!");
    }

}
class CallingFeaturesStack extends Contacts
{
    class Node1
    {
        int NumberData;
        String NameData;
        double Duration;
        CallingFeaturesStack.Node1 next;
    }
    double t=0;
    CallingFeaturesStack.Node1 head=null;
    // Calling Function, You can call any number and depending on ur contacts it'll display the name and when you end the call , the duration will also be showed
    void MakeACall(int Number)
    {
        if(balance>0) {
            System.out.println("\t\t\t\t\t  Now Calling\t\t");
            if (NameGiver(Number) == (null)) {
                System.out.println("\t\t\t\t---------" + Number + "---------");
                System.out.println();
                t = Duration();
                balance = balance - (int) t * 2;
                System.out.println("Call Duration: "+t);
            } else {
                System.out.println("\t\t\t\t---------" + NameGiver(Number) + "---------");
                System.out.println();
                t = Duration();
                balance = balance - (int) t * 2;
                System.out.println("Call Duration: "+t);
            }
            if (NameGiver(Number) == (null)) {
                push(Number, "Unknown", t);
            } else {
                push(Number, NameGiver(Number), t);
            }
        }else{
            System.out.println("You Don't Have Enough Credit To Make A Call Pls RECHARGE First");
        }
    }
    void push(int Number,String Name,double Duration){
        Node1 newnode1=new Node1();
        newnode1.NumberData=Number;
        newnode1.NameData=Name;
        newnode1.Duration=Duration;
        if(head==null){
            head=newnode1;
            return;
        }
        newnode1.next=head;
        head=newnode1;
    }
    double Duration()
    {
        Scanner sc=new Scanner(System.in);
        long start,end;
        double tim;
        start=System.currentTimeMillis();
        System.out.println("Press -1 to exit call");
        int x=sc.nextInt();
        end=System.currentTimeMillis();
        tim=(end-start)/1000.0;
        return tim;
    }
    public void Recents()
    {
        Node1 curr=head;
        if(head==null){
            System.out.println("Empty");
        }
        while(curr!=null) {
            System.out.println("Name: " + curr.NameData + "====>" + "Number: " + curr.NumberData + "====>" + "Duration: " + curr.Duration);
            curr=curr.next;
        }
    }
}
class Aesthetics
{
    //The whole look of the code when executed
    int IntroWork(){
        System.out.println();
        System.out.println("\t\t\t\t===============Contact App===============\t\t\t\t");
        Contacts c=new Contacts();
        Scanner sc=new Scanner(System.in);
        System.out.println("1.Add A Contact\n2.Delete A Contact\n3.Search A Contact\n4.Display All Contacts\n5.Edit Contact\n6.Make A Call\n7.Recent Call Logs\n8.Trash Display\n9.Search History\n10.Restore Deleted Contacts\n11.Balance Recharge\n12.Balance Display\n___________________:");
        int ch=sc.nextInt();
        return ch;
    }
}
public class Main
{
    public static void main(String[] args) throws IOException
    {
        String n;
        int num;
        String t;
        String f;
        Scanner sc=new Scanner(System.in);
        Aesthetics a=new Aesthetics();
        CallingFeaturesStack c=new CallingFeaturesStack();
        BufferedReader br = new BufferedReader(new FileReader("DataSet.txt"));
        Scanner scanner = new Scanner(new File("DataSet.txt"));
        scanner.useDelimiter(",");
        String line = br.readLine();
        while(line!=null)
        {
            n=scanner.next();
            num= Integer.parseInt(scanner.next());
            t= scanner.next();
            f=scanner.nextLine();
            c.AddContact(num,n,t,f);
            line= br.readLine();
        }
        br.close();

        while (true)
        {
            int k = a.IntroWork();
            if(k==1)
            {
                System.out.println("Enter Name");
                String name = sc.next();
                System.out.println("Enter Number");
                int number = sc.nextInt();
                System.out.println("Enter tag");
                String tag  = sc.next();
                System.out.println("Enter Favourite");
                String fav = sc.next();
                int a2 = c.DuplicateCheckerByNames(name);
                int b2 = c.DuplicateCheckerByNumbers(number);
                if( a2> 0 && b2 > 0)
                {
                    System.out.println("------This Contact Already Exist!------");
                    System.out.println("Contact Details: "+name+"===>"+number);
                }else if(a2>0)
                {
                    System.out.println("------Duplicate Name("+name+") Found!------");
                    System.out.println("Would You Like To Add It?\n1.Yes\n2.No: ");
                    int p=sc.nextInt();
                    if(p==1) {
                        c.AddContact(number, name, tag, fav);
                        BufferedWriter bw = new BufferedWriter(new FileWriter("DataSet.txt", true));
                        bw.write(name + "," + number + "," + tag + "," + fav + ",\n");
                        bw.close();
                    }else{

                    }
                }else if(b2>0){
                    System.out.println("------Duplicate Number("+number+")Found!------");
                    System.out.println("Would You Like To Add It?\n1.Yes\n2.No: ");
                    int p=sc.nextInt();
                    if(p==1){
                        c.AddContact(number, name, tag, fav);
                        BufferedWriter bw = new BufferedWriter(new FileWriter("DataSet.txt", true));
                        bw.write(name + "," + number + "," + tag + "," + fav + ",\n");
                        bw.close();
                    }else{

                    }
                }else{
                    c.AddContact(number, name, tag, fav);
                    BufferedWriter bw = new BufferedWriter(new FileWriter("DataSet.txt", true));
                    bw.write(name + "," + number + "," + tag + "," + fav + ",\n");
                    bw.close();
                }
            }
            else if (k == 2)
            {
                System.out.println("Enter Name Of The Contact: ");
                String name=sc.next();
                System.out.println("Enter Number Of The Contact: ");
                int number=sc.nextInt();
                c.SearchForDeletionOnly(name,number);
            }
            else if (k == 3)
            {
                System.out.println("Enter Name Of The Contact: ");
                String name=sc.next();
                c.IterationForSearchFunction(name);
            }
            else if (k == 4)
            {
                System.out.println("1.Normal Display\n2.Sorted Display\n3.Tags\n4.Favourite");
                int ch=sc.nextInt();
                if(ch==1)
                {
                    c.Display(ch,null);
                }
                else if(ch==2)
                {
                    System.out.println("1.A-Z\n2.Z-A");
                    int op = sc.nextInt();
                    if(op==1)
                    {
                        c.Display(ch,"A");
                    }
                    else {
                        c.Display(ch,"Z");
                    }
                }
                else if(ch==3)
                {
                    System.out.println("1.Work\n2.Home\n3.School\n");
                    int choice = sc.nextInt();
                    if(choice == 1)
                    {
                        c.Display(3,"Work");
                    }
                    else if(choice==2)
                    {
                        c.Display(3,"Home");
                    } else if(choice==3)
                    {
                        c.Display(3,"School");
                    }
                }
                else if(ch==4)
                {
                    System.out.println("1.Favourite\n2.Non-Favourite");
                    int choice = sc.nextInt();
                    if(choice == 1)
                    {
                        c.Display(4,"Favourite");
                    }
                    else if(choice==2)
                    {
                        c.Display(4,"Non-Favourite");
                    }
                }
            }
            else if(k == 5)
            {
                System.out.println("Enter Name Of The Contact You Want To Edit: ");
                String name=sc.next();
                System.out.println("Enter Number Of The Contact You Want To Edit: ");
                int numb=sc.nextInt();
                System.out.println("Do You Want To Edit \n1.Name\n2.Number:\n");
                int zx=sc.nextInt();
                if(zx==1){
                    System.out.print("Enter Edited Name: ");
                    String z2=sc.next();
                    c.TraversingFunctionForEditName(name,numb,z2);
                }
                else
                {
                    System.out.print("Enter Edited Number: ");
                    int z2=sc.nextInt();
                    c.TraversingFunctionForEditNumber(name,numb,z2);
                }
            }else if(k==6){
                System.out.println("Dial Number: ");
                int number=sc.nextInt();
                c.MakeACall(number);
            }else if(k==7){
                System.out.println("\t\t\t\t\tRecent Calls Made");
                c.Recents();
            }else if(k==8){
                System.out.println("\t\t\t\t\tTrash Display");
                c.Trashdisplay();
            }else if(k==9){
                System.out.println("\t\t\t\t\tSearch History Display");
                c.SearchDisplay();
            }
            else if(k==10)
            {
                System.out.println("Enter Restore Name");
                String lovename = sc.next();
                System.out.println("Enter Restore Number");
                int lovenum = sc.nextInt();
                c.revieve(lovenum,lovename);
            }else if(k==11){
                System.out.println("Enter Recharge Amount: ");
                int x=sc.nextInt();
                c.BalanceRecharge(x);
                System.out.println("Recharge Successful Enjoy!");
            }else if(k==12){
                System.out.println("Your Current Balance is "+ c.BalanceCheck());
            }
        }
    }
}