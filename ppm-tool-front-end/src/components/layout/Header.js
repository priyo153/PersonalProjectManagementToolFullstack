import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "../../actions/securityActions";

class Header extends Component {
  logout() {
    this.props.logout();
    window.location.href = "/";
  }
  render() {
    const { validToken, user } = this.props.security;

    const userIsNotAUthenticated = (
      <div className="container">
        <Link className="navbar-brand" to="/">
          Personal Project Management Tool
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#mobile-nav"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="mobile-nav">
          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link className="nav-link " to="/login">
                Login
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/register">
                Register
              </Link>
            </li>
          </ul>
        </div>
      </div>
    );

    const userIsAUthenticated = (
      <div className="container">
        <Link className="navbar-brand" to="/dashboard">
          Personal Project Management Tool
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#mobile-nav"
        >
          <span className="navbar-toggler-icon" />
        </button>
        <div className="collapse navbar-collapse" id="mobile-nav">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/dashboard">
                Dashboard
              </Link>
            </li>
          </ul>

          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link className="nav-link " to="/dashboard">
                <i className="fas fa-user-circle mr-2" />
                {user.name}
              </Link>
            </li>
            <li className="nav-item">
              <Link
                className="nav-link"
                to="/logout"
                onClick={this.logout.bind(this)}
              >
                Logout
              </Link>
            </li>
          </ul>
        </div>
      </div>
    );

    let headerLinks;

    if (user && validToken) {
      headerLinks = userIsAUthenticated;
    } else {
      headerLinks = userIsNotAUthenticated;
    }
    return (
      <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        {headerLinks}
      </nav>
    );
  }
}

Header.propTypes = {
  logout: PropTypes.func.isRequired,
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security
});

export default connect(mapStateToProps, { logout })(Header);
