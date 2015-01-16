package A3;

import java.util.Hashtable;
import java.util.Vector;

public class SymbolTable
{
	public SymbolTable()
	{
		this.hashTable = new Hashtable<String, Vector<SymbolTableItem>>();
	}
	
	public boolean insert(String id, String type, String scope)
	{
		Vector<SymbolTableItem> vector = getVectorFrom(id);
		if(vector == null)
		{
			vector = new Vector<SymbolTableItem>();
			vector.add(new SymbolTableItem(type, scope, null));
			hashTable.put(id, vector);
			return true;
		}
		else if(!scopeExistsIn(vector, scope))
		{
			vector.add(new SymbolTableItem(type, scope, null));
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean delete(String id, String scope)
	{
		Vector<SymbolTableItem> vector = getVectorFrom(id);
		if(vector != null)
		{
			SymbolTableItem item = getSymbolTableItem(scope, vector);
			if(item != null)
			{
				vector.remove(item);
				return true;
			}
		}
		return false;
	}
	
	public boolean assignValueTo(String id, String scope, String value)
	{
		Vector<SymbolTableItem> vector = getVectorFrom(id);
		if(vector != null)
		{
			SymbolTableItem item = getSymbolTableItem(scope, vector);
			if(item != null)
			{
				item.setValue(value);
				return true;
			}
		}
		
		return false;
	}
	
	private Vector<SymbolTableItem> getVectorFrom(String id)
	{
		return hashTable.get(id);
	}
	
	private boolean scopeExistsIn(Vector<SymbolTableItem> vector, String scope)
	{
		int length = vector.size();
		for(int i = 0; i < length; i++)
		{
			if(vector.get(i).getScope().equals(scope))
			{
				return true;
			}
		}
		return false;
	}
	
	private SymbolTableItem getSymbolTableItem(String withScope, Vector<SymbolTableItem> fromVector)
	{
		int length = fromVector.size();
		for(int i = 0; i < length; i++)
		{
			SymbolTableItem item = fromVector.get(i);
			if(item.getScope().equals(withScope))
			{
				return item;
			}
		}
		return null;
	}
	
	private Hashtable<String, Vector<SymbolTableItem>> hashTable;
}
