package com.carapp.util;

import java.util.ArrayList;

public class UploadDataInfo {
	public static  String strBranch, strSaleperson, strCustomer, strContactNo, strAddress,
	strMake, strModel, strYear, strOdomstrer, strRegistration, strDate,
	strTime;
	
	public static String 
    tyer_condition_lf,      tyer_condition_lb,       tyer_condition_rf,      tyer_condition_rb,
    tyre_size_lf,           tyre_size_lb,            tyre_size_rf,           tyre_size_rb,
    tyre_depth_lfx,         tyre_depth_lbx,          tyre_depth_rfx,          tyre_depth_rbx,
	tyre_depth_lfy,         tyre_depth_lby,          tyre_depth_rfy,          tyre_depth_rby,
    brake_pad_lf,           brake_pad_lb,            brake_pad_rf,           brake_pad_rb, 
    brake_disk_lf,          brake_disk_lb,           brake_disk_rf,          brake_disk_rb,
    shocker_lf,             shocker_lb,              shocker_rf,             shocker_rb, 
    wheel_lf,               wheel_lb,                wheel_rf,               wheel_rb,
	physical_damage_lf,     physical_damage_lb,      physical_damage_rf,     physical_damage_rb, 
	
	           immoblizer_f,         spare_wheel_b,
                 battery_f,            lock_nut_b,  
            physical_damage_f,    physical_damage_b;
	 
	public static ArrayList<String>  datacust_reson_for_visit,datadealer_recomendation,
	datacust_approved_work;
	
	public static String radiodata,Quotation1,Quotation2;
	public static String serverData;

	public static String company,email;                       
	
	public static String FF, FL,FM,FR,FSL,FSR,BSL,BSR,BL,BM,BR;
	
	public static String observations,wheel_nuts_torqued,wheels_cleaned,wheels_balanced,
	alignment_done,tyre_pressure_front,tyre_pressure_back,tyres_polished,lock_nut_returned,
	car_tested_by_salesperson,work_inspected_by_salesperson,work_approved_by_salesperson,
	customer_satisfied;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UploadDataInfo []";
	}

	
	
}
