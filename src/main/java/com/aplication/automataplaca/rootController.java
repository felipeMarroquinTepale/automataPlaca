package com.aplication.automataplaca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Arrays;

public class rootController {
    @FXML
    private Button bottom_atras;
    @FXML
    private TextField textFieldPlaca;
    @FXML
    private TextArea Textarea_placa;
    @FXML
    private Button botoomValidarPlaca;

    char[] alfabeto={'A','B','C','D','E','F','G','H','J','K','L','M','N','P',
            'R','S','T','U','V','W','X','Y','Z'};
    char[] numeros={'0','1','2','3','4','5','6','7','8','9'};
    int[] transiciones = new int[8];

    //transiciones  yucatan automovil
    int automovilY1[] = {1, 3, 4,5,6,7,8,9};
    int automovilY2[] = {2,3,4,5,6,7,8,9};
    //transiciones Camiones yucatan
    int CamionesY1[] = {1,11,12,5,6,7,8,9};


    //Transiciones  zacatecas automovil
    int automovilZ1[] = {2,13,4,5,6,7,8,9};
    //Transiciones  zacatecas camiones
    int CamionesZ1[] = {2,14,12,5,6,7,8,9};
    int CamionesZ2[] = {1,14,12,5,6,7,8,9};



    public void volverAtras(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root= loader.load();
        Scene newScene= new Scene(root);
        Main.primaryStage.setScene(newScene);
    }
    @FXML
    void validarPlaca(ActionEvent event)throws IOException {





        //System.out.println(textFieldPlaca.getText());
        if (textFieldPlaca.getText().length() == 0 || textFieldPlaca.getText().length() != 9 ) {
            System.out.println("La placa no corresponde al formato");
            Textarea_placa.setText("La placa no corresponde al formato");
        } else {

            char[] cadenaChars = textFieldPlaca.getText().toCharArray();

            for (int i=0;i<cadenaChars.length; i++){
                if (Character.isUpperCase(cadenaChars[i])){
                    System.out.println("Cadena con caracter mayuscula");
                }else {
                    System.out.println("Cadena con caracter minuscula");
                    Textarea_placa.setText("Incorrecto no se aceptan letras minusculas");
                }
            }

            if (cadenaChars[3] == '-' && cadenaChars[7] == '-') {
                //System.out.println("Cadena de Automoviles");
                q0(cadenaChars);
            } else {
                if (cadenaChars[2] == '-' && cadenaChars[7] == '-') {
                    // System.out.println("Cadena Caminones");
                    q0(cadenaChars);
                } else {
                    System.out.println("La placa no corresponde al formato");
                    Textarea_placa.setText("La placa no corresponde al formato");
                }
            }

        }
    }

    public void q0(char[] cadenaChars){
        if(cadenaChars[0]=='Y'){
            q1(cadenaChars);
        }else {
            if(cadenaChars[0]=='Z'){
                q2(cadenaChars);
            }else {
                System.out.printf("Invalido el rango no corresponde a yucatan y a zacatecas");
                Textarea_placa.setText("Invalido el rango no corresponde a yucatan y a zacatecas");
            }
        }
    }

    public void q1(char[] cadenaChars){
        if((cadenaChars[3]=='-') && (cadenaChars[1]=='W' || cadenaChars[1]=='X' || cadenaChars[1]=='Y' || cadenaChars[1]=='Z')){
            transiciones[0]=1;
            q3(cadenaChars);
        }
        else{
            if ((cadenaChars[2]=='-') && (cadenaChars[1]=='N'|| cadenaChars[1]=='P' || cadenaChars[1]=='R' || cadenaChars[1]=='S' || cadenaChars[1]=='T' || cadenaChars[1]=='U')){
                transiciones[0]=1;
                q11(cadenaChars);
            } else {

                if((cadenaChars[2]=='-') && (cadenaChars[1]=='V' || cadenaChars[1]=='W' || cadenaChars[1]=='X' || cadenaChars[1]=='Y' || cadenaChars[1]=='Z')){
                    transiciones[0]=1;
                    q14(cadenaChars);
                }else {
                    Textarea_placa.setText("invalido, el caracter: "+cadenaChars[1]+"no corresponde");
                    System.out.println("Invalido rango, el caracter: "+ cadenaChars[1]+" no corresponde");
                }
            }
        }
    }
    public void q2(char[] cadenaChars){
        if((cadenaChars[3]=='-') &&(cadenaChars[1]=='A'|| cadenaChars[1]=='B' || cadenaChars[1]=='C')){
            transiciones[0]=2;
            q3(cadenaChars);
        }else{
            if ((cadenaChars[3]=='-') && (cadenaChars[1]=='D' || cadenaChars[1]=='E'|| cadenaChars[1]=='F' || cadenaChars[1]=='G'|| cadenaChars[1]=='H')){
                transiciones[0]=2;
                q13(cadenaChars);
            }else {
                if (cadenaChars[2]=='-') {
                    for (int i = 0; i < 9; i++) {
                        if (cadenaChars[1] == alfabeto[i]) {
                            transiciones[0] = 2;
                            q14(cadenaChars);
                        }
                    }
                }else {
                    System.out.println("Invalido rango, el caracter: "+ cadenaChars[1]+" no corresponde");
                    Textarea_placa.setText("invalido, el caracter: "+cadenaChars[1]+" no corresponde");
                }
            }
        }
    }

    public void q3(char[] cadenaChars){
        for (int i=0;i<alfabeto.length; i++){
            if (cadenaChars[2]==alfabeto[i]){
                if(transiciones[0]==1){
                    transiciones[1]=3;
                    q4(cadenaChars);
                }else{
                    if(transiciones[0]==2){
                        transiciones[1]=3;
                        q4(cadenaChars);
                    }
                }
            }
        }
    }
    public void q4(char[] cadenaChars){
        if (cadenaChars[3]=='-'){
            if(transiciones[1]==3){
                transiciones[2]=4;
                q5(cadenaChars);
            }else {
                if(transiciones[1]==13){
                    transiciones[2]=4;
                    q5(cadenaChars);
                }
            }
        } else {
            System.out.println("Invalido rango, el caracter: "+ cadenaChars[3]+" no corresponde");
            Textarea_placa.setText("invalido, el caracter: "+cadenaChars[3]+" no corresponde");
        }
    }
    public void q5(char[] cadenaChars){
        for (int i=0;i<numeros.length; i++){
            if (cadenaChars[4]==numeros[i]){
                transiciones[3]=5;
                q6(cadenaChars);
            }
        }
    }
    public void q6(char[] cadenaChars){
        for (int i=0;i<numeros.length; i++){
            if (cadenaChars[5]==numeros[i]){
                transiciones[4]=6;
                q7(cadenaChars);
            }
        }
    }
    public void q7(char[] cadenaChars){
        for (int i=1;i<numeros.length; i++){
            if (cadenaChars[6]==numeros[i]){
                transiciones[5]=7;
                q8(cadenaChars);
            }
        }
    }
    public void q8(char[] cadenaChars){
        if (cadenaChars[7]=='-'){
            transiciones[6]=8;
            q9(cadenaChars);
        }
    }
    public void q9(char[] cadenaChars){
        for (int i=0;i<alfabeto.length; i++){
            if (alfabeto[i]==cadenaChars[8]){
                transiciones[7]=9;
                q10(cadenaChars);
            }
        }
    }
    public void q10(char[] cadenaChars){
        System.out.println("Aceptado");
        for (int i=0;i<transiciones.length;i++){
            System.out.print("---> q"+transiciones[i]);
        }

        if (Arrays.equals(transiciones,automovilY1 ) || Arrays.equals(transiciones,automovilY2)){
            System.out.println("Placa automovil de yucatan");
            Textarea_placa.setText("");
            Textarea_placa.setText("Su placa de automovil corresponde al estado de yucatan");
        } else {
            if(Arrays.equals(transiciones,CamionesY1)){
                System.out.println("Placa camiones de yucatan");
                Textarea_placa.setText("");
                Textarea_placa.setText("Su placa de camiones corresponde al estado de yucatan");
            }else {
                if (Arrays.equals(transiciones,automovilZ1)){
                    System.out.println("Placa automoviles de zacatecas");
                    Textarea_placa.setText("");
                    Textarea_placa.setText("Su placa de automovil corresponde al estado de zacatecas");
                }else {
                    if(Arrays.equals(transiciones,CamionesZ1) || Arrays.equals(transiciones,CamionesZ2)){
                        System.out.println("Placa camiones de zacatecas");
                        Textarea_placa.setText("");
                        Textarea_placa.setText("Su placa de camiones corresponde al estado de zacatecas");
                    }
                }
            }
        }
    }
    public void q11(char[] cadenaChars){
        if (cadenaChars[2]=='-'){
            transiciones[1]=11;
            q12(cadenaChars);
        }
    }
    public void q12(char[] cadenaChars){
        for (int i=0;i<numeros.length; i++){
            if (cadenaChars[3]==numeros[i]){
                if(transiciones[1]==11){
                    transiciones[2]=12;
                    q5(cadenaChars);
                }else {
                    if(transiciones[1]==14){
                        transiciones[2]=12;
                        q5(cadenaChars);

                    }
                }
            }
        }
    }
    public void q13(char[] cadenaChars){
        for (int i=0;i<alfabeto.length; i++){
            if (alfabeto[i]== cadenaChars[2]){
                transiciones[1]=13;
                q4(cadenaChars);
            }
        }
    }
    public void q14(char[] cadenaChars){
        if (cadenaChars[2]=='-'){
            if(transiciones[0]==1){
                transiciones[1]=14;
                q12(cadenaChars);
            }else {
                transiciones[1]=14;
                q12(cadenaChars);
            }
        }
    }
}