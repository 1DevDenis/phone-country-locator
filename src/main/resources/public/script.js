document.addEventListener('DOMContentLoaded', function() {
    const phoneForm = document.getElementById('phoneForm');
    const resultDiv = document.getElementById('hiddenResultId');
    const countryElement = document.getElementById('country');
    const phoneNumberInput = document.getElementById('phoneNumber');

    phoneForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        console.log('Form submitted');
        const phoneNumber = phoneNumberInput.value;

        if (!validatePhoneNumber(phoneNumber)) {
           alert('Invalid phone number. Please enter a valid phone number.');
           return;
        }

        const response = await fetch(`/api/v1/country-calling-code/${phoneNumber}`);
        const data = await response.text();

        if (response.ok) {
            console.log('Data from server:', data);
            countryElement.textContent = `Country: ${data}`;
        } else {
            countryElement.textContent = 'Error: Country not found';
        }

        resultDiv.classList.remove('hiddenResult');
        console.log('Hidden class removed');
        });

    function validatePhoneNumber(phoneNumber) {
        const phonePattern = /^\d{1,12}$/;
        return phonePattern.test(phoneNumber);
    }

    phoneNumberInput.addEventListener('input', function(event) {
        const inputValue = event.target.value;
        event.target.value = inputValue.replace(/\D/g, '').substr(0, 12);
    });
});
