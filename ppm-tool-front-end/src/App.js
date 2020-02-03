import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/project/AddProject";
import UpdateProject from "./components/project/UpdateProject";
import { Provider } from "react-redux";
import store from "./store";
import ProjectBoard from "./components/projectBoard/ProjectBoard";
import AddProjectTask from "./components/projectBoard/projectTasks/AddProjectTask";
import UpdateProjectTask from "./components/projectBoard/projectTasks/UpdateProjectTask";
import Landing from "./components/layout/Landing";
import Register from "./components/userManagement/Register";
import Login from "./components/userManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecuredRoute";
import NotFound from "./components/userManagement/NotFound";

const JwtToken = localStorage.jwtToken;
if (JwtToken) {
  setJWTToken(JwtToken);
  const decoded = jwt_decode(JwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded
  });

  var currentTime = Date.now();
  currentTime = currentTime.toString();
  currentTime = currentTime.slice(0, -3);
  currentTime = parseInt(currentTime);

  if (decoded.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          <Route exact path="/" component={Landing} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/login" component={Login} />
          <SecuredRoute exact path="/dashboard" component={Dashboard} />
          <SecuredRoute exact path="/addProject" component={AddProject} />
          <SecuredRoute
            exact
            path="/updateProject/:id"
            component={UpdateProject}
          />
          <SecuredRoute
            exact
            path="/projectBoard/:id"
            component={ProjectBoard}
          />
          <SecuredRoute
            exact
            path="/addProjectTask/:id"
            component={AddProjectTask}
          />
          <SecuredRoute
            exact
            path="/updateProjectTask/:backlog_id/:task_id"
            component={UpdateProjectTask}
          />
          <Route exact path="*" component={NotFound} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
