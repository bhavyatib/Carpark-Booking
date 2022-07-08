package com.example.oop_project_47;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Checkout_Reminder {
    // Find your Account Sid and Auth Token at twilio.com/console
    //public static final String ACCOUNT_SID = System.getenv("AC22a079333dfce2bb0f6f868e667d1360");
    //public static final String AUTH_TOKEN = System.getenv("e7a500e12786e1ba915f5d2e69c79b24");

    public static void main(String[] args) {
        Twilio.init(
                System.getenv("TWILIO_ACCOUNT_SID"),
                System.getenv("TWILIO_AUTH_TOKEN"));
        Message message = Message
                .creator(new PhoneNumber("+91"+ "9911213758"), // to
                        new PhoneNumber("+18507897596"), // from
                        "")
                .create();

        System.out.println(message.getSid());
    }
}