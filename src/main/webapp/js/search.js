function searchTable(tableId, searchInputId)
{
	const table = document.getElementById(tableId);
	const searchInput = document.getElementById(searchInputId);
	
	searchInput.addEventListener("keyup", function()
	{
		const searchTerm = this.value.toLowerCase();
		const tableRows = table.querySelectorAll("tbody tr");

		for (let i = 0; i < tableRows.length; i++)
		{
			const row = tableRows[i];
			const tableData = row.querySelectorAll("td");
			let found = false;
      
			if (row.rowIndex === 0)
			{
				continue;
			}
			for (let j = 0; j < tableData.length;  j++)
			{
				const cellText = tableData[j].textContent.toLowerCase();
				if (cellText.indexOf(searchTerm) !== -1)
				{
					found = true;
  					break;
				}
			}
			if (found)
			{
				row.style.display = "";
			}
			else
			{
				row.style.display = "none";
			}
		}
	});
}