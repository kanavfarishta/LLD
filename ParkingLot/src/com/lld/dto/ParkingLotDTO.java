package com.lld.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLotDTO
{

	private int numOfLevels;
	private int spotsBike;
	private int spotsCar;
	private int spotsTruck;
	private ArrayList<HashMap<String, ArrayList<ParkingSpace>>> parkedVehicles;
	private ArrayList<HashMap<String, Integer>> avaialbleCounter;
	private int spotsBikeAvailable;
	private int spotsCarAvailable;
	private int spotsTruckAvailable;

	public ParkingLotDTO(int numOfLevels, int spotsBike, int spotsCar, int spotsTruck)
	{
		super();
		this.numOfLevels = numOfLevels;
		this.spotsBike = spotsBike;
		this.spotsCar = spotsCar;
		this.spotsTruck = spotsTruck;
		for (int i = 0; i < numOfLevels; i++)
		{
			HashMap<String, Integer> map = new HashMap<String, Integer>()
			{
				{
					put("Bike", spotsBike);
					put("Car", spotsCar);
					put("Trucks", spotsTruck);
				}
			};

			this.avaialbleCounter.add(map);
		}
		this.spotsBikeAvailable = numOfLevels * spotsBike;
		this.spotsCarAvailable = numOfLevels * spotsCar;
		this.spotsTruckAvailable = numOfLevels * spotsTruck;
	}

	private void decrementSpot(String typeV)
	{
		if (typeV == "Bike")
		{
			spotsBikeAvailable--;
		}
		else if (typeV == "Car")
		{
			spotsCarAvailable--;
		}
		else
		{
			spotsTruckAvailable--;
		}
	}

	private void incrementSpot(String typeV)
	{
		if (typeV == "Bike")
		{
			spotsBikeAvailable++;
		}
		else if (typeV == "Car")
		{
			spotsCarAvailable++;
		}
		else
		{
			spotsTruckAvailable++;
		}
	}

	private int getSpotsForType(String typeV)
	{
		if (typeV == "Bike")
		{
			return spotsBike;
		}
		else if (typeV == "Car")
		{
			return spotsCar;
		}
		else
		{
			return spotsTruck;
		}
	}

	public String parkVehicle(Vehicle vehicle) throws Exception
	{
		if (totalAvailableNoOfParkingSpace() == 0)
		{
			return "Parking Full";
		}
		else if (availableNoOfParkingSpace(vehicle.type) == 0)
		{
			return "Parking Full for this Type";
		}
		else
		{

			if (parkedVehicles.size() == 0)
			{
				HashMap<String, ArrayList<ParkingSpace>> hMap = new HashMap<>();
				ArrayList<ParkingSpace> parkSpaceArray = new ArrayList<>();
				ParkingSpace parkSpace = new ParkingSpace(0, 0, vehicle);
				parkSpaceArray.add(parkSpace);
				decrementSpot(vehicle.type);
				hMap.put(vehicle.type, parkSpaceArray);
				return "Vehicle parked at Level : " + parkSpace.getLevel() + "at position : " + parkSpace.getPosition();
			}
			else
			{
				for (int level = 0; level < parkedVehicles.size(); level++)
				{
					HashMap<String, ArrayList<ParkingSpace>> hMap = parkedVehicles.get(level);
					ArrayList<ParkingSpace> vehiclesInside = hMap.get(vehicle.type);
					if (vehiclesInside == null)
					{
						ArrayList<ParkingSpace> parkSpaceArray = new ArrayList<>();
						ParkingSpace parkSpace = new ParkingSpace(level, 0, vehicle);
						parkSpaceArray.add(parkSpace);
						decrementSpot(vehicle.type);
						hMap.put(vehicle.type, parkSpaceArray);
						return "Vehicle parked at Level : " + parkSpace.getLevel() + "at position : "
								+ parkSpace.getPosition();
					}
					else
					{
						if (vehiclesInside.size() > getSpotsForType(vehicle.type))
						{
							continue;
						}
						else
						{
							ParkingSpace parkSpace = new ParkingSpace(level, vehiclesInside.size(), vehicle);
							vehiclesInside.add(parkSpace);
							decrementSpot(vehicle.type);
							return "Vehicle parked at Level : " + parkSpace.getLevel() + "at position : "
									+ parkSpace.getPosition();

						}
					}
				}

				return "Parking Full for this Type";
			}

		}

	}

	public String removeParkedVehicle(String type,String licence) throws Exception
	{
		if (totalAvailableNoOfParkingSpace() == this.numOfLevels * (this.spotsBike + this.spotsCar + this.spotsTruck))
		{
			return "No Vehicle parked";
		}
		else
		{

			if (parkedVehicles.size() == 0)
			{
				return "No Vehicle parked";
			}
			else
			{
				for (int level = 0; level < parkedVehicles.size(); level++)
				{
					HashMap<String, ArrayList<ParkingSpace>> hMap = parkedVehicles.get(level);
					ArrayList<ParkingSpace> vehiclesInside = hMap.get(type);
					if (vehiclesInside == null)
					{
						continue;
					}
					else
					{
						for (ParkingSpace parkingSpace : vehiclesInside)
						{
							if (parkingSpace.getVehicleDetails().getLicence().equals(licence))
							{
								vehiclesInside.remove(parkingSpace);
								incrementSpot(type);
								return "Vehicle parked from parking at Level : " + parkingSpace.getLevel()
										+ "at position : " + parkingSpace.getPosition();
							}
						}

					}
				}

				return "No such Vehicle Found";
			}

		}
	}

	public int totalAvailableNoOfParkingSpace()
	{
		return (spotsBikeAvailable + spotsCarAvailable + spotsTruckAvailable);
	}

	private int avaialbleCounterIterator(String type)
	{
		int countValue = 0;
		for (int i = 0; i < numOfLevels; i++)
		{
			countValue += avaialbleCounter.get(i).get(type);
		}
		return countValue;
	}

	public int availableNoOfParkingSpace(String type) throws Exception
	{
		if (type == null)
		{
			throw new Exception("Invalid Type");
		}
		else
		{
			if (type.equals("Bike"))
			{
				return avaialbleCounterIterator("Bike");
			}
			else if (type.equals("Truck"))
			{
				return avaialbleCounterIterator("Truck");
			}
			else if (type.equals("Car"))
			{
				return avaialbleCounterIterator("Car");
			}
			else
			{
				throw new Exception("Invalid Type");
			}
		}
	}

	public int getNumOfLevels()
	{
		return numOfLevels;
	}

	public void setNumOfLevels(int numOfLevels)
	{
		this.numOfLevels = numOfLevels;
	}

	public void setSpotsBike(int spotsBike)
	{
		this.spotsBike = spotsBike;
	}

	public void setSpotsCar(int spotsCar)
	{
		this.spotsCar = spotsCar;
	}


	public void setSpotsTruck(int spotsTruck)
	{
		this.spotsTruck = spotsTruck;
	}

	public int getSpotsBikeAvailable()
	{
		return spotsBikeAvailable;
	}

	public void setSpotsBikeAvailable(int spotsBikeAvailable)
	{
		this.spotsBikeAvailable = spotsBikeAvailable;
	}

	public int getSpotsCarAvailable()
	{
		return spotsCarAvailable;
	}

	public void setSpotsCarAvailable(int spotsCarAvailable)
	{
		this.spotsCarAvailable = spotsCarAvailable;
	}

	public int getSpotsTruckAvailable()
	{
		return spotsTruckAvailable;
	}

	public void setSpotsTruckAvailable(int spotsTruckAvailable)
	{
		this.spotsTruckAvailable = spotsTruckAvailable;
	}
	
	
}
