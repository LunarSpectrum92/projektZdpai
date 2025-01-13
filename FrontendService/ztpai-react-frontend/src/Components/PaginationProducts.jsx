import React from "react";
import Pagination from "react-bootstrap/Pagination";

const PaginationProducts = ({ paginationNumber, activePage, onPageChange }) => {
  let items = [];
  for (let number = 1; number <= paginationNumber; number++) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === activePage}
        onClick={() => onPageChange(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <div className="d-flex justify-content-center">
      <Pagination>{items}</Pagination>
    </div>
  );
};

export default PaginationProducts;
