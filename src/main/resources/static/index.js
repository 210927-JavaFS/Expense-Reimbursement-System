const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let logoutButton = document.getElementById('logoutButton');
let buttonRow = document.getElementById("buttonRow");
let registerButton = document.getElementById("registerButton");
let requestReimbButton = document.getElementById('requestReimbButton');
let showReimbsButton = document.createElement("button");
let getByStatusButton = document.getElementById("reimbByStatus");
let getByIdButton = document.getElementById("reimbById");
let getMyReimbsButton = document.getElementById("myReimbs");
let approveButton = document.getElementById("approve");
let denyButton = document.getElementById("deny");

loginButton.onclick = login; 
logoutButton.onclick = logout;
registerButton.onclick = addUser;
requestReimbButton.onclick = addReimb;
showReimbsButton.onclick = showReimbs;
getByStatusButton.onclick = getByStatus;
getByIdButton.onclick = getById;
getMyReimbsButton.onclick = getMyReimbs;
approveButton.onclick = approve;
denyButton.onclick = deny;


showReimbsButton.innerText = "View All Reimbursements";
showReimbsButton.className ="btn btn-primary";

buttonRow.appendChild(showReimbsButton);
document.getElementById("buttonRow").style.display = 'block';

async function getMyReimbs(){
  let login = JSON.parse(sessionStorage.getItem("login"));
  let response = await fetch(URL + "ErsReimbursement/user/" + login.ersUserId, {credentials:"include"});

  if(response.status === 200){
    let data = await response.json();
    console.log(data);
    populateReimbTable(data);
  }
  else{
    console.log("Could not find your tickets");
  }
}

async function updateReimb(status){
  let Id = document.getElementById("statusUpdate").value;
  let oldReimb = await getByIdUpdate();
  let newReimbStatusId;
  let newReimbAmount = oldReimb.reimbAmount;
  let newReimbDescription = oldReimb.reimbDescription;
  let newReimbSubmitted = oldReimb.reimbSubmitted;
  let newReimbAuthor = oldReimb.reimbAuthor;
  let newReimbTypeId = oldReimb.reimbTypeId;
  
  switch(status){
    case 'Approved':
    newReimbStatusId = {
      reimbStatusId:2,
      status:"Approved"
    };
    break;

    case 'Denied':
    newReimbStatusId = {
      reimbStatusId:3,
      status:"Denied"
    };
    break;
  }

  let reimb = {
    reimbId:Id,
    reimbAmount:newReimbAmount,
    reimbSubmitted:newReimbSubmitted,
    reimbResolved:Date.now(),
    reimbDescription:newReimbDescription,
    reimbAuthor:newReimbAuthor,
    reimbResolver:JSON.parse(sessionStorage.getItem("login")),
    reimbStatusId:newReimbStatusId,
    reimbTypeId:newReimbTypeId
  };
  return reimb;
}

async function deny(){
  let login = JSON.parse(sessionStorage.getItem("login"));
  console.log(login.userRoleId.role);
  if (login.userRoleId.role==="Manager"){
    let reimb = await updateReimb("Denied");
    let Id = document.getElementById("statusUpdate").value;
    let response = await fetch(URL+"ErsReimbursement/" + Id, {
      method:'PUT',
      body:JSON.stringify(reimb),
      credentials:"include"
    });
  
    if (response.status===200){
      console.log("Denied")
    }
    else{
      console.log("Failed")
    }
  }
  else{
    console.log("You cant do this");
  }

}

async function approve(){
  let login = JSON.parse(sessionStorage.getItem("login"));

  let reimb = await updateReimb("Approved");
  console.log(reimb);
  let Id = document.getElementById("statusUpdate").value;
  let response = await fetch(URL+"ErsReimbursement/" + Id, {
    method:'PUT',
    body:JSON.stringify(reimb),
    credentials:"include"
  });

  if (response.status===200){
    console.log("Approved")
  }
  else{
    console.log("Failed")
  }

}


async function getById(){
  let Id = document.getElementById("Id").value;
  let response = await fetch(URL+"ErsReimbursement/"+Id, {credentials:"include"});
  if(response.status === 200){
    let data = await response.json();
    console.log(data);
    populateReimbTable(data);
    
  }
  else{
    console.log("Failed to get reimb.")
  }
}

async function getByIdUpdate(){
  let Id = document.getElementById("statusUpdate").value;
  let response = await fetch(URL+"ErsReimbursement/"+Id, {credentials:"include"});
  if(response.status === 200){
    let data = await response.json();
    return data[0];
    
  }
  else{
    console.log("Failed to get reimb.")
  }
}

async function getByStatus(){
  let statusId = document.getElementById("status").value;
  let response = await fetch(URL+"ErsReimbursement/status/"+statusId, {credentials:"include"});
  if(response.status === 200){
    let data = await response.json();
    populateReimbTable(data);
  }
  else{
    console.log("Failed to get reimbs.")
  }

}


function getNewUser(){
  let newUsername = document.getElementById("newusername").value;
  let newPassword = document.getElementById("newpassword").value;
  let newFirstName = document.getElementById("firstName").value;
  let newLastName = document.getElementById("lastName").value;
  let newEmail = document.getElementById("email").value;
  let newRole = document.getElementById("role").value;

  switch(newRole){
    case 'Employee':
    newRole = {
      ersUserRoleId:1,
      role:newRole
    };
    break;

    case 'Manager':
    newRole = {
      ersUserRoleId:2,
      role:newRole
    };
    break;

  }

  let newUser =  {
    ersUsername:newUsername,
    ersPassword:newPassword,
    userFirstName:newFirstName,
    userLastName:newLastName,
    userEmail:newEmail,
    userRoleId:newRole,
  }
  console.log(newUser);
  return newUser;
}
async function addUser(){
  let user = getNewUser();
    
      let response = await fetch(URL+"ErsUser", {
        method:'POST',
        body:JSON.stringify(user),
        credentials:"include"
      });
    
      if(response.status===201){
        console.log("User created successfully.");
      }else{
        console.log("Something went wrong creating your User.")
      }
}
//get user
async function getUser(username){
  let response = await fetch(URL+"ErsUser/"+username, {credentials:"include"});
  if(response.status === 200){
    let data = await response.json();
    return data;
  }
  else{
    console.log("Failed to get user.")
  }
}

function logout(){
  location.reload();
}
//login
async function login(){
  sessionStorage.clear();
    let user = {
      username:document.getElementById("username").value,
      password:document.getElementById("password").value
    }
    console.log(user);
    let response = await fetch(URL+"Login", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include" 
      });
      console.log(response.status);
      if(response.status===200){
        let data = await getUser(user.username);
        sessionStorage.setItem("login",JSON.stringify(data));
      

      }
      else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementById("formClass").appendChild(para);
      }
    }

    //view all reimbs

    async function showReimbs(){
      let response = await fetch(URL+"ErsReimbursement", {credentials:"include"});
      if(response.status===200){
        let data = await response.json();
        populateReimbTable(data);
        document.getElementById("reimbTable").style.display = 'block';
      }else{
        console.log("Reimbs not available.");
      }
    }



    function populateReimbTable(data){

      let tbody = document.getElementById("reimbBody");

      while(tbody.rows.length > 0) {
        tbody.deleteRow(0);
      }

      for(let reimb of data){
        let row = document.createElement("tr");
        for(let cell in reimb){
          let td = document.createElement("td");
          if (cell!="reimbAuthor" && cell!="reimbResolver" && cell!="reimbStatus" && cell!="reimbType") {
            td.innerText = reimb[cell];
          }
          if (cell=="reimbAuthor") td.innerText = `${reimb[cell].ersUsername}`;
          if (cell == "reimbResolver" && reimb[cell]!=null) td.innerText = `${reimb[cell].ersUsername}`;
          if (cell=="reimbStatusId") td.innerText = `${reimb[cell].status}`;
          if (cell=="reimbTypeId") td.innerText = `${reimb[cell].type}`;
          if(cell == "submittedDate") td.innerText = `${new Date(reimb[cell])}`;
          if(cell == "resolvedDate")
            if (reimb[cell]!=null) td.innerText = `${new Date(reimb[cell])}`;
    
          row.appendChild(td);
        }
        tbody.appendChild(row);
      }
    }
    
    function getNewReimb(){
      let newReimbAmount = document.getElementById("reimbAmount").value;
      let newReimbSubmitted = Date.now();
      let newReimbResolved = null;
      let newReimbDescription = document.getElementById("reimbDescription").value;
      let newReimbAuthor = JSON.parse(sessionStorage.getItem("login"));
      let newReimbResolver = null;
      let newReimbStatusId;
      let newReimbTypeId = document.getElementById("reimbTypeId").value;
      


      newReimbStatusId = {
        reimbStatusId:1,
        status:"Pending"
      }
      switch(newReimbTypeId){
        case 'LODGING':
        newReimbTypeId = {
          reimbTypeId:1,
          type:newReimbTypeId
        };
        break;

        case 'TRAVEL':
        newReimbTypeId = {
          reimbTypeId:2,
          type:newReimbTypeId
        };
        break;

        case 'FOOD':
        newReimbTypeId = {
          reimbTypeId:3,
          type:newReimbTypeId
        };
        break;

        case 'OTHER':
        newReimbTypeId = {
          reimbTypeId:4,
          type:newReimbTypeId
        };
        break;
      }
    
     

      let reimb =  {
        reimbAmount:parseFloat(newReimbAmount),
        reimbSubmitted:newReimbSubmitted,
        reimbResolved:newReimbResolved,
        reimbDescription:newReimbDescription,
        reimbAuthor:newReimbAuthor,
        reimbResolver:newReimbResolver,
        reimbStatusId:newReimbStatusId,
        reimbTypeId:newReimbTypeId
    
      }
      console.log(reimb);
      return reimb;
    }
    
    async function addReimb(){
      let reimb = getNewReimb();
    
      let response = await fetch(URL+"ErsReimbursement", {
        method:'POST',
        body:JSON.stringify(reimb),
        credentials:"include"
      });
    
      if(response.status===201){

        console.log("Reimb created successfully.");
      }else{
        console.log("Something went wrong creating your Reimb.")
      }
    
    }