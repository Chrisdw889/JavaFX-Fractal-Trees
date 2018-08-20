package fractalTrees;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class TreeControls
{
	private FractalTree tree;
	private Main parent;
	private Slider branchAngle, branchBalance, lengthRefactor;
	private Label baLabel, bbLabel, lrLabel;
	
	public TreeControls(FractalTree tree)
	{
		super();
		
		this.tree = tree;
		this.parent = null;
		
		baLabel = new Label("Branch Angle");
		bbLabel = new Label("Branch Balance");
		lrLabel = new Label("Length Refactor");
		
		branchAngle = new Slider();
		branchBalance = new Slider();
		lengthRefactor = new Slider();
		
		setUp();
	}
	
	public void setUp()
	{
		branchAngle.setMin(0);
		branchAngle.setMax(2 * Math.PI);
		branchAngle.setValue(tree.getBranchAngle());
		branchAngle.valueProperty().addListener(new ChangeListener<Number>() 
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
			{
				tree.setBranchAngle(newValue.doubleValue());
				parent.redraw();
			}
		});
		
		branchBalance.setMin(-0.2);
		branchBalance.setMax(0.2);
		branchBalance.setValue(tree.getBranchBalance());
		branchBalance.valueProperty().addListener(new ChangeListener<Number>() 
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
			{
				tree.setBranchBalance(newValue.doubleValue());
				parent.redraw();
			}
		});
		
		lengthRefactor.setMax(0.6);
		lengthRefactor.setMin(0.3);
		lengthRefactor.setValue(tree.getLengthRefactor());
		lengthRefactor.valueProperty().addListener(new ChangeListener<Number>() 
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
			{
				tree.setLengthRefactor(newValue.doubleValue());
				parent.redraw();
			}
		});
	}

	//Setters
	public void setParent(Main parent)
	{
		this.parent = parent;
	}
	
	//Getters
	public Slider getBranchAngle()
	{
		return branchAngle;
	}

	public Slider getBranchBalance()
	{
		return branchBalance;
	}

	public Slider getLengthRefactor() 
	{
		return lengthRefactor;
	}
	
	public Label getBaLabel() 
	{
		return baLabel;
	}

	public Label getBbLabel() 
	{
		return bbLabel;
	}

	public Label getLrLabel() 
	{
		return lrLabel;
	}
	
	
}
