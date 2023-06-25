import React from 'react';

function PersonInput({ person, updatePersonName, addItem, updateItemName, updateItemPrice }) {
  const handlePersonNameChange = (e) => {
    updatePersonName(person.id, e.target.value);
  };

  const handleItemNameChange = (itemId, e) => {
    updateItemName(person.id, itemId, e.target.value);
  };

  const handleItemPriceChange = (itemId, e) => {
    updateItemPrice(person.id, itemId, parseFloat(e.target.value));
  };

  return (
    <div>
      <h3>Pessoa {person.id}</h3>
      <label htmlFor={`personName${person.id}`}>Nome:</label>
      <input
        type="text"
        id={`personName${person.id}`}
        name={`personName${person.id}`}
        value={person.name}
        onChange={handlePersonNameChange}
        required
      />
      <br />
      <h4>Itens:</h4>
      {person.items.map((item, index) => (
        <div key={index}>
          <label htmlFor={`itemName${person.id}-${index}`}>Nome do Item:</label>
          <input
            type="text"
            id={`itemName${person.id}-${index}`}
            name={`itemName${person.id}-${index}`}
            value={item.name}
            onChange={(e) => handleItemNameChange(index, e)}
            required
          />
          <br />
          <label htmlFor={`itemPrice${person.id}-${index}`}>Preço do Item:</label>
          <input
            type="number"
            id={`itemPrice${person.id}-${index}`}
            name={`itemPrice${person.id}-${index}`}
            step="0.01"
            value={item.price}
            onChange={(e) => handleItemPriceChange(index, e)}
            required
          />
          <br />
        </div>
      ))}
      <button type="button" onClick={() => addItem(person.id)}>
        Adicionar Item
      </button>
    </div>
  );
}

export default PersonInput;