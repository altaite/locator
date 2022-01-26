/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.altaite.locator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author anton
 */
public class WT {
    //frame

    static JFrame f;

    //lists
    static JList b;

    public static void main(String[] args) {
        // java Program to create a simple JList

        //create a new frame
        f = new JFrame("frame");

     
        //create a panel
        JPanel p = new JPanel();

        //create a new label
        JLabel l = new JLabel("select the day of the week");

        //String array to store weekdays
        String week[] = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"};

        //create list
        b = new JList(week);

        //set a selected index
        b.setSelectedIndex(2);

        //add list to panel
        p.add(b);

        f.add(p);

        //set the size of frame
        f.setSize(400, 400);

        f.show();
    }

}
