package io.mmaltsev.vkeducation.domain.appdetails

class ToggleWishlistUseCase(
    private val appDetailsRepository: AppDetailsRepository
) {
    suspend operator fun invoke(id: String) = appDetailsRepository.toggleWishlist(id)
}