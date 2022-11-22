$(async function () {
    await newUser();
});

async function newUser() {
    /*
    await fetch("http://localhost:8080/api/roles")
        .then(res => res.json())
        .then(roleList => {
            roleList.forEach(role => {
                let el = document.createElement("option");
                el.text = role.role.substring(5);
                el.value = role.id;
                $('#newUserRoles')[0].appendChild(el);
            })
        }
        )
     */

    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();

        const selected_options = document.querySelector('#AddNewUserRoles').selectedOptions;

        let rolesNamesArray = new Array(selected_options.length);
        for (let i = 0; i < selected_options.length; i++) {
            rolesNamesArray[i] = selected_options[i].value;
        }
  //      let rolesNames = rolesNamesArray;
  //      console.log(rolesNamesArray);
  //      console.log(rolesNames);
  //      alert("Stop here to check rolesNames");

        fetch("http://localhost:8080/api/new", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: form.name.value,
                secondName: form.secondName.value,
                age: form.age.value,
                email: form.email.value,
                password: form.password.value,
                roles: rolesNamesArray
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsers').click();
        })
    }
}

