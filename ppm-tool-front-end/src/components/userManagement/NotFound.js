import React, { Component } from "react";
import { connect } from "react-redux";

class NotFound extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rendered: props.rendered
    };
  }

  componentDidMount() {}
  componentWillReceiveProps(nextProps) {
    this.setState({
      rendered: nextProps.rendered
    });
  }

  render() {
    //alert(this.state.rendered);
    const { rendered } = this.state;
    if (rendered === false) {
      return (
        <div class="d-flex flex-column  align-items-center">
          <h1 class="errormsg d-flex">404</h1>
          <h4 class="errormsg d-flex pb-5">
            The page you are looking for doesnt exist
          </h4>
          <div class="notFoundPic" />
        </div>
      );
    } else {
      return <div></div>;
    }
  }
}

const mapStateToProps = state => ({
  rendered: state.rendered
});

export default connect(mapStateToProps)(NotFound);
