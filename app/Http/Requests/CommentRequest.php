<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class CommentRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    
    public function rules()
    {
        return [
            'commentText' => 'required|string|max:500',
        ];
    }
}
