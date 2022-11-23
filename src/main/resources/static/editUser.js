$(async function() {
    editUser();
});
function editUser() {
    const editForm = document.forms["formEditUser"];
    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        if (editForm.roleList !== undefined) {
            for (let i = 0; i < editForm.roleList.options.length; i++) {
                if (editForm.roleList.options[i].selected) editUserRoles.push({
                    id: editForm.roleList.options[i].value,
                    role: "ROLE_" + editForm.roleList.options[i].text
                })
            }
        }

        fetch("http://localhost:8080/api/edit/" + editForm.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editForm.id.value,
                name: editForm.name.value,
                secondName: editForm.secondName.value,
                age: editForm.age.value,
                email: editForm.email.value,
                password: editForm.password.value,
                roleList: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            allUsers();
        })
    })
}