const tableBody = document.getElementById('tableBody');
const addButton = document.getElementById('addButton');
let rowCount = 1;
//  nextIndex = 0; // Keeps track of the next element index
addButton.addEventListener('click', function()
{
    const newRow = document.createElement('tr');
    newRow.classList.add('dataRow');

    const cell1 = document.createElement('td');
    const cell2 = document.createElement('td');
    const cell3 = document.createElement('td');
    const cell4 = document.createElement('td');
    const cell5 = document.createElement('td');
    const cell6 = document.createElement('td');
    const cell7 = document.createElement('td');

    // Add input fields or content to cells (replace with your logic)
    cell1.innerHTML = `<select name="type[${rowCount}]" class="form-control medtype" required>
		                    	<option value="Tab">Tab</option>
		                    	<option value="Cap">Cap</option>
		                    	<option value="Syrup">Syrup</option>
	                   </select>`;
    cell2.innerHTML = `<input type="text" name="medicine[${rowCount}]" style="text-transform: capitalize;" placeholder="Medicine Name" value="" class="medname" required />`;
    cell3.innerHTML = `<input type="text" name="measure[${rowCount}]" placeholder="Eg. 1 cap/1 tab/30 ml" value="" class="measure" required/>`;
    cell4.innerHTML = `<div>
	                        <div>
	                        	<input type="checkbox" id="Morning" name="dosage[${rowCount}][]" value="Morning" checked>
	                        	<label for="Morning">M</label>
	                        </div>
	                        
	                        <div>
		                        <input type="checkbox" id="Afternoon" name="dosage[${rowCount}][]" value="Afternoon">
		                        <label for="Afternoon">A</label>
	                        </div>
	                        
	                        <div>
		                        <input type="checkbox" id="Evening" name="dosage[${rowCount}][]" value="Evening">
		                        <label for="Evening">E</label>
	                        </div>
		               </div>`;
    cell5.innerHTML = `<select name="instruction[${rowCount}]" class="instruction" required>
	                             <option value="After Meal">After Meal</option>
	                             <option value="Before Meal">Before Meal</option>
	                   </select>`;
    cell6.innerHTML = `<input type="number" name="days[${rowCount}]" placeholder="Days" value="" class="days" required />`;

    // Create and add "Remove" button with click event listener
    const removeButton = document.createElement('button');
    removeButton.textContent = 'Remove';
    removeButton.classList.add('remove-button');  // Add a class for styling (optional)
    removeButton.addEventListener('click', function()
    {
        // this.parentElement.remove();  // Remove the parent row (dataRow)
        this.closest('tr').remove();
        rowCount--;
    });

    cell7.appendChild(removeButton);
    newRow.appendChild(cell1);
    newRow.appendChild(cell2);
    newRow.appendChild(cell3);
    newRow.appendChild(cell4);
    newRow.appendChild(cell5);
    newRow.appendChild(cell6);
    newRow.appendChild(cell7);

    tableBody.appendChild(newRow);
    rowCount++;
});