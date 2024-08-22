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
	
	public String addVehicle()
	{
		return "";
	}

}
