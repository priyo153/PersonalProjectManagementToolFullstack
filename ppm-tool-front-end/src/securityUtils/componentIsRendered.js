import store from "../store";

export default function componentIsRendered() {
  store.dispatch({
    type: "RENDERED"
  });
}
