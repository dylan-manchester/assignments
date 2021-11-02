import { render, screen } from '@testing-library/react';
import App from './App';

test('Renders header and footer', () => {
  render(<App />);
  const headerElement = screen.getByText("Employee Management App");
  expect(headerElement).toBeInTheDocument();
});
