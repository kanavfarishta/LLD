package com.lld.dto;

public class ParkingLotManager
{
	private static volatile ParkingLotManager instance;
	private ParkingLotDTO parkingLot;

	public static ParkingLotManager getInstance()
	{
		if (instance == null)
		{
			synchronized (ParkingLotManager.class)
			{
				if (instance == null)
				{
					instance = new ParkingLotManager();
				}
			}

		}

		return instance;
	}

	public void createParkingLot(int levels, int bikeSpace, int carSpace, int TruckSpace)
	{
		this.parkingLot = new ParkingLotDTO(levels, bikeSpace, carSpace, TruckSpace);
	}
	
	private Vehicle vehicleCreator(String type,String licence)
	{
		if(type=="Car")
		{
			return new Car(licence);
		}
		if(type=="Truck")
		{
			return new Truck(licence);
		}
		if(type=="Bike")
		{
			return new Bike(licence);
		}
		return null;
	}
	
	public String addVehicle(String type,String licence) throws Exception
	{
	
		Vehicle vehicleCreated = vehicleCreator(type,licence);
		return parkingLot.parkVehicle(vehicleCreated);
	}
	
	public String removeVehicle(String type,String licence) throws Exception
	{
		return parkingLot.removeParkedVehicle(type, licence);
	}
	
	public String getParkingAvailability()
	{
		return "Available for Bike : " +parkingLot.getSpotsBikeAvailable()+
		"Available for Cars : " +parkingLot.getSpotsCarAvailable()+
		"Available for Trucks : "+parkingLot.getSpotsTruckAvailable();
	}

}
