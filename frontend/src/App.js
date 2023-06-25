import React, { useState } from 'react';
import QRCode from 'react-qr-code';
import PersonInput from './PersonInput';

function App() {
  const [personCount, setPersonCount] = useState(0);
  const [people, setPeople] = useState([]);
  const [discount, setDiscount] = useState((0).toFixed(2));
  const [surcharge, setSurcharge] = useState((0).toFixed(2));
  const [deliveryFee, setDeliveryFee] = useState((0).toFixed(2));
  const [paymentWallet, setPaymentWallet] = useState('PICPAY');
  const [discountPercent, setDiscountPercent] = useState(false);
  const [surchargePercent, setSurchargePercent] = useState(false);
  const [results, setResults] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');

  const addPerson = () => {
    setPersonCount(personCount + 1);
    const newPerson = { id: personCount + 1, name: '', items: [] };
    setPeople([...people, newPerson]);
  };

  const updatePersonName = (personId, newName) => {
    const updatedPeople = people.map((person) => {
      if (person.id === personId) {
        return { ...person, name: newName };
      }
      return person;
    });
    setPeople(updatedPeople);
  };

  const addItem = (personId) => {
    const updatedPeople = people.map((person) => {
      if (person.id === personId) {
        const newItem = { name: '', price: (0).toFixed(2) };
        return { ...person, items: [...person.items, newItem] };
      }
      return person;
    });
    setPeople(updatedPeople);
  };

  const updateItemName = (personId, itemId, newName) => {
    const updatedPeople = people.map((person) => {
      if (person.id === personId) {
        const updatedItems = person.items.map((item, index) => {
          if (index === itemId) {
            return { ...item, name: newName };
          }
          return item;
        });
        return { ...person, items: updatedItems };
      }
      return person;
    });
    setPeople(updatedPeople);
  };

  const updateItemPrice = (personId, itemId, newPrice) => {
    const updatedPeople = people.map((person) => {
      if (person.id === personId) {
        const updatedItems = person.items.map((item, index) => {
          if (index === itemId) {
            return { ...item, price: newPrice };
          }
          return item;
        });
        return { ...person, items: updatedItems };
      }

      return person;
    });
    setPeople(updatedPeople);
  };

  const formatFloat = (number) => {
    return parseFloat(number).toFixed(2);
  };

  const calculateDivision = (e) => {
    e.preventDefault();

    if (people.length === 0) {
      alert('Adicione pelo menos uma pessoa.');
      return;
    }

    const formattedPeople = people.map((person) => ({
      ...person,
      items: person.items.map((item) => ({
        ...item,
        price: formatFloat(item.price),
      })),
    }));

    const order = {
      people: formattedPeople,
      discount: formatFloat(discount),
      surcharge: formatFloat(surcharge),
      deliveryFee: formatFloat(deliveryFee),
      paymentWallet: paymentWallet,
      discountPercent: discountPercent,
      surchargePercent: surchargePercent,
    };

    console.log(JSON.stringify(order));

    // Envio do pedido para o backend
    fetch('/calculate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(order),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(response.statusText);
        }
        return response.json();
      })
      .then((data) => {
        setResults(data);
        setErrorMessage('');
      })
      .catch((error) => {
        console.error('Ocorreu um erro:', error);
        setErrorMessage('A soma dos preços dos itens não pode ser 0.');
      });
  };

  return (
    <div>
      <h1>Divisão do Almoço</h1>
      <form onSubmit={calculateDivision}>
        <h2>Informações do Pedido</h2>
        <div id="peopleContainer">
          {people.map((person) => (
            <PersonInput
              key={person.id}
              person={person}
              updatePersonName={updatePersonName}
              addItem={addItem}
              updateItemName={updateItemName}
              updateItemPrice={updateItemPrice}
            />
          ))}
        </div>
        <button type="button" onClick={addPerson}>
          Adicionar Pessoa
        </button>
        <br />
        <h2>Informações Adicionais</h2>
        <label htmlFor="discount">Desconto:</label>
        <input
          type="number"
          id="discount"
          name="discount"
          step="0.01"
          value={discount}
          onChange={(e) => setDiscount(parseFloat(e.target.value))}
          required
        />
        <br />
        <div style={{ display: "flex", alignItems: "center" }}>
          <input
            style={{ marginRight: "8px" }}
            type="checkbox"
            id="discountPercent"
            name="discountPercent"
            checked={discountPercent}
            onChange={(e) => setDiscountPercent(e.target.checked)}
          />
          <label htmlFor="discountPercent" style={{ position: "relative", top: "-4px" }}>
            Desconto em Porcentagem
          </label>
        </div>
        <br />
        <label htmlFor="surcharge">Taxa Extra:</label>
        <input
          type="number"
          id="surcharge"
          name="surcharge"
          step="0.01"
          value={surcharge}
          onChange={(e) => setSurcharge(parseFloat(e.target.value))}
          required
        />
        <br />
        <div style={{ display: "flex", alignItems: "center" }}>
          <input
            type="checkbox"
            id="surchargePercent"
            name="surchargePercent"
            checked={surchargePercent}
            onChange={(e) => setSurchargePercent(e.target.checked)}
          />
          <label htmlFor="surchargePercent" style={{ position: "relative", top: "-4px" }}>
            Taxa Extra em Porcentagem
          </label>
        </div>
        <br />
        <br />
        <label htmlFor="deliveryFee">Taxa de Entrega:</label>
        <input
          type="number"
          id="deliveryFee"
          name="deliveryFee"
          step="0.01"
          value={deliveryFee}
          onChange={(e) => setDeliveryFee(parseFloat(e.target.value))}
          required
        />
        <br />
        <br />
        <label htmlFor="paymentWallet">Carteira de Pagamento:</label>
        <select
          id="paymentWallet"
          name="paymentWallet"
          value={paymentWallet}
          onChange={(e) => setPaymentWallet(e.target.value)}
          required
        >
          <option value="PICPAY">PICPAY</option>
        </select>
        <br />
        <br />
        <button type="submit">Calcular Divisão Proporcional</button>
      </form>

      {results.length > 0 && (
        <div>
          <h2>Resultado:</h2>
          <ul>
            {results.map((person) => (
              <li key={person.name}>
                {person.name}:
                <p>
                  Valor a ser pago: R$ {formatFloat(person.paymentValue)}
                  <br />
                  Link para pagamento: <a href={person.paymentLink}>{person.paymentLink}</a>
                  <br />
                </p>
                <div style={{ marginTop: '20px', display: 'flex', alignItems: 'center' }}>
                  <span style={{ marginRight: '10px' }}>QRCode: </span>
                  <QRCode value={person.paymentLink} size={128} />
                </div>
              </li>
            ))}
          </ul>
        </div>
      )}

      {errorMessage && (
        <div>
          <h2>Erro:</h2>
          <p>{errorMessage}</p>
        </div>
      )}
    </div>
  );
}

export default App;