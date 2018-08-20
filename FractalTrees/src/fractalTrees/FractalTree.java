package fractalTrees;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Path;

public class FractalTree extends Path
{
	private int branchLength;
	private double branchAngle;
	private double branchBalance;
	private double lengthRefactor;
	
	private int startX, startY;
	
	private static final double DEFAULT_BRANCH_ANGLE = Math.PI / 4;
	private static final double DEFAULT_BRANCH_BALANCE = 0.0;
	private static final int DEFAULT_BRANCH_LENGTH = 200;
	private static final double DEFAULT_LENGTH_REFACTOR = 0.6;
	
	public FractalTree(int x, int y)
	{
		super();
		
		startX = x;
		startY = y;
		
		this.branchAngle = DEFAULT_BRANCH_ANGLE;
		this.branchBalance = DEFAULT_BRANCH_BALANCE;
		this.branchLength = DEFAULT_BRANCH_LENGTH;
		this.lengthRefactor = DEFAULT_LENGTH_REFACTOR;
		
	}
	
	public void draw(GraphicsContext gc)
	{
		gc.moveTo(startX, startY);
		branch(gc, branchLength, startX, startY, 0);
	}
	
	public void branch(GraphicsContext gc, int length, int startX, int startY, double rotation) 
	{
		if(length <= 1)
		{
			return;
		}
		
		int canopyX = (int) Math.round(startX + length * Math.sin(rotation));
		int canopyY = (int) Math.round(startY - length * Math.cos(rotation));
		
		gc.lineTo(canopyX, canopyY);
		
		//branch left
		int leftLength = (int) Math.round((length * lengthRefactor) * (1 - branchBalance));
		
		branch(gc, leftLength, canopyX, canopyY, rotation - branchAngle);
		
		gc.moveTo(canopyX,  canopyY);
		
		//branch right
		int rightLength = (int) Math.round((length * lengthRefactor) * (1 + branchBalance));
		
		branch(gc, rightLength, canopyX, canopyY, rotation + branchAngle);
		
	}
	
	//Getters
	public int getBranchLength()
	{
		return this.branchLength;
	}
	
	public double getBranchAngle()
	{
		return this.branchAngle;
	}
	
	public double getBranchBalance()
	{
		return this.branchBalance;
	}
	
	public double getLengthRefactor()
	{
		return this.lengthRefactor;
	}

	//Setters
	public void setBranchLength(int branchLength) 
	{
		this.branchLength = branchLength;
	}

	public void setBranchAngle(double branchAngle) 
	{
		this.branchAngle = branchAngle;
	}

	public void setBranchBalance(double branchBalance) 
	{
		this.branchBalance = branchBalance;
	}

	public void setLengthRefactor(double lengthRefactor) 
	{
		this.lengthRefactor = lengthRefactor;
	}
	
}
