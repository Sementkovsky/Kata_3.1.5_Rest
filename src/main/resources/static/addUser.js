$(async function() {
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


        /*
        e.preventDefault();
        let newUserRoles = [];
        if (form.roleList !== undefined) {
           for (let i = 0; i < form.roleList.options.length; i++) {
               if (form.roleList.options[i].selected) newUserRoles.push({
                   id: form.roleList.options[i].value,
                    role: form.roleList.options[i].role
               }
                )
            }
        }
        */


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
                roleList: newUserRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsersTable').click();
        })
    }

}
