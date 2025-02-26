/* eslint-disable react/prop-types */
import React from 'react'

export default function CartItem({cartItem}) {
  return (
    <div key={cartItem.productId} className="border-b py-4">
    <h3 className="text-lg font-semibold">{cartItem.productId}</h3>
    <p className="text-gray-800">Price: ${cartItem.quantity}</p>
    <p className="text-gray-800">Stock: {cartItem.price}</p>
  </div>
  )
}
