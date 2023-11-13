// Import the functions you need from the SDKs you need
// import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

import { initializeApp, database as _database } from "firebase";
// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDLzmZyt5nZwCk98iZ6wi01y7Jxio1ppZQ",
  authDomain: "fine-bondedwarehouse.firebaseapp.com",
  databaseURL: "https://fine-bondedwarehouse-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "fine-bondedwarehouse",
  storageBucket: "fine-bondedwarehouse.appspot.com",
  messagingSenderId: "415417723331",
  appId: "1:415417723331:web:15212f190062886281b576",
  measurementId: "G-SWBR4359JQ"
};

// Initialize Firebase
// const app = initializeApp(firebaseConfig);
initializeApp(firebaseConfig)
let database = _database();
// export default database;
// module.exports = database;
// const analytics = getAnalytics(app);