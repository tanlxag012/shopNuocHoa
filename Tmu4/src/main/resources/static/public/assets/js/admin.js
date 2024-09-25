const list = [];
document.addEventListener('DOMContentLoaded', function () {
    const perfumeForm = document.getElementById('perfumeForm');
    const perfumeTable = document.getElementById('perfumeTable').getElementsByTagName('tbody')[0];
    const submitItems = document.getElementById('submitItems');
    let perfumes = [];
    let editingId = null;

    perfumeForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('perfumeId').value;
        const name = document.getElementById('name').value;
        const brand = document.getElementById('brand').value;
        const description = document.getElementById('description').value;
        const price = document.getElementById('price').value;
        const quantity = document.getElementById('quantity').value;

        const item = {
            id:id,
            name:name,
            description:description,
            price:price,
            quantity:quantity,
            perfumeType: brand
        }
        list.push(item)

        if (editingId) {
            const perfume = perfumes.find(p => p.id === editingId);
            perfume.name = name;
            perfume.brand = brand;
            perfume.price = price;
            perfume.quantity = quantity;
            perfume.description = description;
            editingId = null;
        } else {
            const newPerfume = {
                id: perfumes.length + 1,
                name: name,
                brand: brand,
                price: price,
                quantity: quantity,
                description : description
            };
            perfumes.push(newPerfume);
        }

        document.getElementById('perfumeId').value = '';
        perfumeForm.reset();
        renderTable();
    });

    function renderTable() {
        perfumeTable.innerHTML = '';
        perfumes.forEach(perfume => {
            const row = perfumeTable.insertRow();
            row.innerHTML = `
                <td>${perfume.id}</td>
                <td>${perfume.name}</td>
                <td>${perfume.brand}</td>
                <td>${perfume.description}</td>
                <td>${perfume.price}</td>
                <td>${perfume.quantity}</td>
                <td>
                    <button onclick="editPerfume(${perfume.id})">Sửa</button>
                    <button onclick="deletePerfume(${perfume.id})">Xóa</button>
                </td>
            `;
        });
    }

    window.editPerfume = function (id) {
        const perfume = perfumes.find(p => p.id === id);
        document.getElementById('perfumeId').value = perfume.id;
        document.getElementById('name').value = perfume.name;
        document.getElementById('brand').value = perfume.brand;
        document.getElementById('description').value = perfume.description;
        document.getElementById('price').value = perfume.price;
        document.getElementById('quantity').value = perfume.quantity;
        editingId = id;
    };

    submitItems.onclick = () => {
        fetch("/api/auth/addPerfumes", {
            method: "POST",
            headers: {
                    "Content-Type": "application/json"
                },
            body: JSON.stringify(list)
        })

    }

    window.deletePerfume = function (id) {
        perfumes = perfumes.filter(p => p.id !== id);
        renderTable();
    };
    getAllPerfumeType();

});

const getAllPerfumeType = () => {
     fetch('/api/auth/perfumeType')
             .then(json => json.json())
             .then(typeList => {
                 const brandElement = document.getElementById("brand");
                 typeList.forEach(type => {
                     let newOption = document.createElement('option');
                 newOption.value = type.name;
                 newOption.text = type.name;
                 brandElement.add(newOption);
                 })
             })
}
