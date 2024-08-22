package com.lld.dto;

public class ParkingSpace
{
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	private int level;
	private int position;
	private Vehicle vehicleDetails;
	
	
	
	public int getPosition()
	{
		return position;
	}
	public void setPosition(int position)
	{
		this.position = position;
	}
	public Vehicle getVehicleDetails()
	{
		return vehicleDetails;
	}
	public void setVehicleDetails(Vehicle vehicleDetails)
	{
		this.vehicleDetails = vehicleDetails;
	}
	public ParkingSpace(int level, int position, Vehicle vehicleDetails)
	{
		super();
		this.level = level;
		this.position = position;
		this.vehicleDetails = vehicleDetails;
	}

}
