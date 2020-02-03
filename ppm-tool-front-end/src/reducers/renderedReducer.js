const initialState = false;
export default function(state = initialState, action) {
  switch (action.type) {
    case "RENDERED":
      return true;
    default:
      return state;
  }
}
