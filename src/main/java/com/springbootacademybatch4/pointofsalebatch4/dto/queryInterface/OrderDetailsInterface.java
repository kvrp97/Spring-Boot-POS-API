package com.springbootacademybatch4.pointofsalebatch4.dto.queryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {
     String getCustomerName();
     String getCustomerAddress();
     ArrayList getContactNumbers();
     Date getDate();
     double getTotal();
}
